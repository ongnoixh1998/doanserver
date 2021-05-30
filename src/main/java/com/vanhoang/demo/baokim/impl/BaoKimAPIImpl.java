package com.vanhoang.demo.baokim.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.baokim.BaoKimAPI;
import com.vanhoang.demo.baokim.model.Bank;
import com.vanhoang.demo.baokim.model.BpmBank;
import com.vanhoang.demo.baokim.model.request.BaoKimOrderRequest;
import com.vanhoang.demo.baokim.model.responses.BankListResponse;
import com.vanhoang.demo.baokim.model.responses.BaoKimOrderResponse;
import com.vanhoang.demo.baokim.model.responses.BpmBankResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Service
public class BaoKimAPIImpl implements BaoKimAPI {
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;
    @Autowired
    BaoKimJWT baoKimJWT;
    final String BASE_URL  = "https://sandbox-api.baokim.vn/payment";
    @Override
    public BankListResponse getBankList() throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("jwt", "Bearer "+baoKimJWT.generateToken());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<BankListResponse> responseEntity = restTemplate.exchange(
                BASE_URL+"/api/v4/bank/list",
                HttpMethod.GET,
                httpEntity,
                BankListResponse.class
        );
        return responseEntity.getBody();
    }

    @Override
    public BpmBankResponse getBpmBankList() throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("jwt", "Bearer "+baoKimJWT.generateToken());
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<BpmBankResponse> responseEntity = restTemplate.exchange(
                BASE_URL+"/api/v4/bpm/list",
                HttpMethod.GET,
                httpEntity,
                BpmBankResponse.class
        );
        return responseEntity.getBody();

    }

    @Override
    public BaoKimOrderResponse payment(BaoKimOrderRequest orderRequest) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("jwt", "Bearer "+baoKimJWT.generateToken());
        HttpEntity<BaoKimOrderRequest> httpEntity = new HttpEntity(orderRequest,headers);
        ResponseEntity<BaoKimOrderResponse> responseEntity = restTemplate.exchange(
                BASE_URL+"/api/v4/order/send",
                HttpMethod.POST,
                httpEntity,
                BaoKimOrderResponse.class
        );
        return responseEntity.getBody();
    }
}
