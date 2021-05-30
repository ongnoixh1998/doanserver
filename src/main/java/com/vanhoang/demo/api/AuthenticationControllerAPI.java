package com.vanhoang.demo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface AuthenticationControllerAPI {
    @GetMapping()
    ResponseEntity<Map> authenticate(@RequestParam("clientID") String clientID,
                                     @RequestParam("secrect") String secrect) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
}
