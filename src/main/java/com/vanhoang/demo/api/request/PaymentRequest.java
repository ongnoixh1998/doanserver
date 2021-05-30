package com.vanhoang.demo.api.request;

public class PaymentRequest {
    String orderId;
    String description;
    String email;
    String phoneNumber;
    Long amount;
    String successUrl;
    String errorUrl;
    public PaymentRequest() {
    }

    public PaymentRequest(String orderId, String description, String email, String phoneNumber, Long amount) {
        this.orderId = orderId;
        this.description = description;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
