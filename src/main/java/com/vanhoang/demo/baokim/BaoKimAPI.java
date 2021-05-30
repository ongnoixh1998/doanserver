package com.vanhoang.demo.baokim;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.baokim.model.Bank;
import com.vanhoang.demo.baokim.model.BpmBank;
import com.vanhoang.demo.baokim.model.request.BaoKimOrderRequest;
import com.vanhoang.demo.baokim.model.responses.BankListResponse;
import com.vanhoang.demo.baokim.model.responses.BaoKimOrderResponse;
import com.vanhoang.demo.baokim.model.responses.BpmBankResponse;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BaoKimAPI {
    BankListResponse getBankList() throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException;
    BpmBankResponse getBpmBankList() throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
    BaoKimOrderResponse payment(BaoKimOrderRequest orderRequest) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
}
