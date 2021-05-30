package com.vanhoang.demo.security;

import java.util.Map;

public class TokenModel {
    Map<String,Object> headers;
    Map<String,Object> payload;
    String signature;

    public Map<String, Object> getHeaders() {

        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
