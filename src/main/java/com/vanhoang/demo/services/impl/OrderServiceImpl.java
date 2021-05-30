package com.vanhoang.demo.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.api.request.PaymentRequest;
import com.vanhoang.demo.baokim.BaoKimAPI;
import com.vanhoang.demo.baokim.model.request.BaoKimOrderRequest;
import com.vanhoang.demo.baokim.model.responses.BaoKimOrderResponse;
import com.vanhoang.demo.config.WebConstants;
import com.vanhoang.demo.entity.OrderEntity;
import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.repository.OrderRepository;
import com.vanhoang.demo.repository.ProviderRepository;
import com.vanhoang.demo.security.JwtToken;
import com.vanhoang.demo.security.TokenModel;
import com.vanhoang.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    JwtToken jwtToken;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BaoKimAPI baoKimAPI;
    final String returnURL = "http://localhost:8080";
    @Override
    public OrderEntity findById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderEntity changeStatus(Integer orderId,String status) {
        OrderEntity orderEntity = findById(orderId);
        if (orderEntity!=null){
            orderEntity.setStatus(status);
            orderEntity = orderRepository.save(orderEntity);
        }
        return orderEntity;
    }

    @Override
    public String getReturnSuccess(Integer orderId) {
        OrderEntity orderEntity = findById(orderId);
        return orderEntity.getSuccessUrl();
    }

    @Override
    public String getReturnError(Integer orderId) {
        OrderEntity orderEntity = findById(orderId);
        return orderEntity.getErrorUrl();
    }

    @Override
    public String tokenPayment(PaymentRequest paymentRequest, String providerId) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        ProviderEntity providerEntity = providerRepository.findByClientId(providerId);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAmount(paymentRequest.getAmount());
        orderEntity.setCreateDate(new Date());
        orderEntity.setDescription(paymentRequest.getDescription());
        orderEntity.setPartnerId(paymentRequest.getOrderId());
        orderEntity.setProviderEntity(providerEntity);
        orderEntity.setSuccessUrl(paymentRequest.getSuccessUrl());
        orderEntity.setErrorUrl(paymentRequest.getErrorUrl());
        orderEntity = orderRepository.save(orderEntity);
        Map map = new HashMap();
        map.put("orderId",orderEntity.getId());
        map.put("user",providerEntity.getClientId());
        Date now = new Date();
        Date expire = new Date(now.getTime()+JwtToken.TOKEN_EXPIRE);
        map.put("iat",now);
        map.put("exp",expire);
        return jwtToken.generateJWT(providerEntity.getKeyAPI(), map);
    }

    @Override
    public BaoKimOrderResponse submitBaokim(String token, String description, String email, String phoneNumber, Integer bpm_id) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        TokenModel tokenModel = jwtToken.decode(token);
        OrderEntity orderEntity  = findById((Integer) tokenModel.getPayload().get("orderId"));
        ProviderEntity providerEntity = providerRepository.findByClientId(String.valueOf(tokenModel.getPayload().get("user")));
        BaoKimOrderRequest orderRequest = new BaoKimOrderRequest();
        orderRequest.setMrc_order_id(orderEntity.getPartnerId());
        orderRequest.setTotal_amount(orderEntity.getAmount());
        orderRequest.setDescription(description);
        /** redirect success url*/
        Map mapSuccess = new HashMap();
        mapSuccess.put("orderId",orderEntity.getId());
        mapSuccess.put("success",true);
        orderRequest.setUrl_success(returnURL+"/payment/success/"+jwtToken.generateJWT(WebConstants.KEY_TOKEN,mapSuccess)+"/");
        /**redirect cancel url*/
        Map mapError = new HashMap();
        mapError.put("orderId",orderEntity.getId());
        mapError.put("success",false);
        orderRequest.setUrl_detail(returnURL+"/payment/error/"+jwtToken.generateJWT(WebConstants.KEY_TOKEN,mapError)+"/");

        orderRequest.setMerchant_id(Long.valueOf(34829));
        orderRequest.setLang("vi");
        orderRequest.setBpm_id(bpm_id);
        orderRequest.setAccept_bank(1);
        orderRequest.setAccept_cc(0);
        orderRequest.setAccept_qrpay(0);
        orderRequest.setAccept_e_wallet(0);
        orderRequest.setWebhooks(providerEntity.getUrlWebhook());
        orderRequest.setCustomer_name("Nguyen Van Hoang");
        orderRequest.setCustomer_email(email);
        orderRequest.setCustomer_phone(phoneNumber);
        orderRequest.setCustomer_address("9/1A giải phóng, Tân Bình, Hồ Chí Minh");
       return baoKimAPI.payment(orderRequest);
    }
}
