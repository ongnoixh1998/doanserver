package com.vanhoang.demo.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.api.PaymentControllerAPI;
import com.vanhoang.demo.api.request.PaymentRequest;
import com.vanhoang.demo.security.JwtToken;
import com.vanhoang.demo.security.TokenModel;
import com.vanhoang.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/admin/payment")
public class PaymentControllerAPIImpl implements PaymentControllerAPI {
    @Autowired
    private Environment env;
    @Autowired
    OrderService orderService;
    @Autowired
    JwtToken jwtToken;
    @Override
    public ResponseEntity<Map> getPayment(PaymentRequest paymentRequest, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        TokenModel tokenModel = jwtToken.decode(getJwtFromRequest(request));
        Map map = new HashMap();
        String token  = orderService.tokenPayment(paymentRequest, String.valueOf(tokenModel.getPayload().get("user")));
        map.put("host",env.getProperty("spring.domain"));
        map.put("urlPayment",env.getProperty("spring.domain")+"/payment?token="+token);
        map.put("token",token);
        return ResponseEntity.ok(map);
    }
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
