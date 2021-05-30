package com.vanhoang.demo.security;


import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.exceptions.authentication.JWTException;
import com.vanhoang.demo.repository.ProviderRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
   @Autowired
   JwtToken jwtToken;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    String[] notIgnoring;
    public JwtAuthenticationFilter(String[] notIgnoring) {
        this.notIgnoring = notIgnoring;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        String jwt = getJwtFromRequest(request);
        try {
            if (StringUtils.hasText(jwt) ) {
                TokenModel tokenModel = jwtToken.decode(jwt);
                String clientId = String.valueOf(tokenModel.getPayload().get("user"));
                ProviderEntity providerEntity = providerRepository.findByClientId(clientId);
                jwtToken.verifyJWT(jwt,providerEntity.getKeyAPI());
                CustomUserDetail userDetails = customUserDetailService.loadUserByUsername(clientId);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }else {
                throw new JWTException("token wrong format","tokenwrongformat");
            }
            filterChain.doFilter(request, response);
        } catch (JWTException e) {
            resolver.resolveException(request, response, null, e);
        }catch (MalformedJwtException e) {
            resolver.resolveException(request, response, null, e);
        }catch (ExpiredJwtException e) {
            resolver.resolveException(request, response, null, e);
        }catch (UnsupportedJwtException e) {
            resolver.resolveException(request, response, null, e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        for (String url:notIgnoring){
            if (Pattern.compile(url).matcher(request.getRequestURI()).find()){
                return false;
            }
        }
        return true;
    }
}
