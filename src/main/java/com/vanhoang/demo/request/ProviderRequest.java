package com.vanhoang.demo.request;

public class ProviderRequest {
    String clientId;
    String clientSecrect;
    String urlWebhook;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecrect() {
        return clientSecrect;
    }

    public void setClientSecrect(String clientSecrect) {
        this.clientSecrect = clientSecrect;
    }

    public String getUrlWebhook() {
        return urlWebhook;
    }

    public void setUrlWebhook(String urlWebhook) {
        this.urlWebhook = urlWebhook;
    }


}
