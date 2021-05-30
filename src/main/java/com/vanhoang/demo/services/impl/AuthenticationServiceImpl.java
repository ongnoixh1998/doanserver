package com.vanhoang.demo.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.repository.ProviderRepository;
import com.vanhoang.demo.security.JwtToken;
import com.vanhoang.demo.security.JwtTokenProvider;
import com.vanhoang.demo.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    JwtToken jwtToken;

    @Override
    public String generateToken(String clientId, String secrectId) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        ProviderEntity providerEntity = providerRepository.findByClientId(clientId);
        if (passwordEncoder.matches(secrectId,providerEntity.getClientSecrect())){
            Map map = new HashMap();
            map.put("user",providerEntity.getClientId());
            Date now = new Date();
            Date expire = new Date(now.getTime()+JwtToken.TOKEN_EXPIRE);
            map.put("iat",now);
            map.put("exp",expire);
            return jwtToken.generateJWT(providerEntity.getKeyAPI(),map);
        }
        return null;
    }
}
