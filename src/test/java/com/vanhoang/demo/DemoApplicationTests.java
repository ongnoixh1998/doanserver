package com.vanhoang.demo;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.vanhoang.demo.baokim.BaoKimAPI;
import com.vanhoang.demo.baokim.impl.BaoKimJWT;
import com.vanhoang.demo.baokim.model.request.BaoKimOrderRequest;
import com.vanhoang.demo.entity.OrderEntity;
import com.vanhoang.demo.entity.ProviderEntity;
import com.vanhoang.demo.exceptions.authentication.JWTException;
import com.vanhoang.demo.repository.ProviderRepository;
import com.vanhoang.demo.security.JwtToken;
import com.vanhoang.demo.services.OrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    BaoKimJWT baoKimJWT;
    @Autowired
    BaoKimAPI baoKimAPI;
    @Autowired
    JwtToken jwtToken;
    @Autowired
    OrderService orderServicel;
    @Autowired
    ProviderRepository providerRepository;
    @Test
    void contextLoads() throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, JWTException {
//        OrderEntity orderEntity  = orderServicel.findById(1);
//        ProviderEntity providerEntity = providerRepository.findByClientId("CLIENT1122");
//        BaoKimOrderRequest orderRequest = new BaoKimOrderRequest();
//        orderRequest.setMrc_order_id("HD12_"+orderEntity.getPartnerId());
//        orderRequest.setTotal_amount(orderEntity.getAmount());
//        orderRequest.setDescription("Thanh toan hoa don "+orderEntity.getPartnerId());
//        orderRequest.setMerchant_id(Long.valueOf(34829));
//        orderRequest.setLang("vi");
//        orderRequest.setBpm_id(97);
//        orderRequest.setAccept_bank(1);
//        orderRequest.setAccept_cc(0);
//        orderRequest.setAccept_qrpay(0);
//        orderRequest.setAccept_e_wallet(0);
//        orderRequest.setWebhooks(providerEntity.getUrlWebhook());
//        orderRequest.setCustomer_name("Nguyen Van Hoang");
//        orderRequest.setCustomer_email("lakdak4@gmail.com");
//        orderRequest.setCustomer_phone("0942492445");
//        orderRequest.setCustomer_address("9/1A giải phóng, Tân Bình, Hồ Chí Minh");
//        baoKimAPI.payment(orderRequest);
    }

}
