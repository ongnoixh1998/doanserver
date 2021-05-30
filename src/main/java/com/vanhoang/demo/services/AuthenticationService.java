package com.vanhoang.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vanhoang.demo.entity.ProviderEntity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AuthenticationService {
    public String generateToken(String clientId, String secrectId) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;
}
