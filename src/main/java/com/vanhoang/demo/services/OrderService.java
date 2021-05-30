package com.vanhoang.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.api.request.PaymentRequest;
import com.vanhoang.demo.baokim.model.responses.BaoKimOrderResponse;
import com.vanhoang.demo.entity.OrderEntity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface OrderService {
    OrderEntity findById(Integer id);
    OrderEntity changeStatus(Integer orderId,String status);
    String getReturnSuccess(Integer orderId);
    String getReturnError(Integer orderId);
    String tokenPayment(PaymentRequest paymentRequest,String providerId) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
    BaoKimOrderResponse submitBaokim(String token,String description,String email,String phoneNumber,Integer bpm_id) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
}
