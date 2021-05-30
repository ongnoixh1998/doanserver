package com.vanhoang.demo.controlleradvice;

import com.vanhoang.demo.exceptions.authentication.JWTException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthenticationControllerAdvice {
    @ExceptionHandler(JWTException.class)
    ResponseEntity<Map> jwtException(JWTException jwtException){
        Map map = new HashMap();
        map.put("success",false);
        map.put("message",jwtException.getMessage());
        map.put("code",jwtException.getCode());
        return ResponseEntity.ok(map);
    }
}
