package com.vanhoang.demo.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.api.AuthenticationControllerAPI;
import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.services.AuthenticationService;
import com.vanhoang.demo.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/authentication")
public class AuthenticationControllerAPIImpl implements AuthenticationControllerAPI {

    @Autowired
    AuthenticationService authenticationService;
    @Override
    public ResponseEntity<Map> authenticate(String clientID, String secrect) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        Map map = new HashMap();
        String token = authenticationService.generateToken(clientID,secrect);
        if (token!=null){
            map.put("success",true);
            map.put("token",token);
            map.put("tokenType","Bearer");
        }else {
            map.put("success",false);
        }
        return ResponseEntity.ok(map);
    }
}
