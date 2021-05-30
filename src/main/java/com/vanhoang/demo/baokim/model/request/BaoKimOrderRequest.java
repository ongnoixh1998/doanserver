package com.vanhoang.demo.baokim.model.request;

public class BaoKimOrderRequest {
    String mrc_order_id;
    Long total_amount;
    String description;
    String url_success;
    Long merchant_id;
    String url_detail;
    String lang;
    Integer bpm_id;
    Integer accept_bank;
    Integer accept_cc;
    Integer accept_qrpay;
    Integer accept_e_wallet;
    String webhooks;
    String customer_email;
    String customer_phone;
    String customer_name;
    String customer_address;

    public String getMrc_order_id() {
        return mrc_order_id;
    }

    public void setMrc_order_id(String mrc_order_id) {
        this.mrc_order_id = mrc_order_id;
    }

    public Long getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Long total_amount) {
        this.total_amount = total_amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_success() {
        return url_success;
    }

    public void setUrl_success(String url_success) {
        this.url_success = url_success;
    }

    public Long getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(Long merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getUrl_detail() {
        return url_detail;
    }

    public void setUrl_detail(String url_detail) {
        this.url_detail = url_detail;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getBpm_id() {
        return bpm_id;
    }

    public void setBpm_id(Integer bpm_id) {
        this.bpm_id = bpm_id;
    }

    public Integer getAccept_bank() {
        return accept_bank;
    }

    public void setAccept_bank(Integer accept_bank) {
        this.accept_bank = accept_bank;
    }

    public Integer getAccept_cc() {
        return accept_cc;
    }

    public void setAccept_cc(Integer accept_cc) {
        this.accept_cc = accept_cc;
    }

    public Integer getAccept_qrpay() {
        return accept_qrpay;
    }

    public void setAccept_qrpay(Integer accept_qrpay) {
        this.accept_qrpay = accept_qrpay;
    }

    public Integer getAccept_e_wallet() {
        return accept_e_wallet;
    }

    public void setAccept_e_wallet(Integer accept_e_wallet) {
        this.accept_e_wallet = accept_e_wallet;
    }

    public String getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(String webhooks) {
        this.webhooks = webhooks;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }
}
