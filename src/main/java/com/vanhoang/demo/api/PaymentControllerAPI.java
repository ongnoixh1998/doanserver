package com.vanhoang.demo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.api.request.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface PaymentControllerAPI {
    @PostMapping()
    ResponseEntity<Map> getPayment(@RequestBody PaymentRequest paymentRequest, HttpServletRequest request) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
}
