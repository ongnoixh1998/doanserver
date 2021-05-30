package com.vanhoang.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider")
public class ProviderEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true)
    String clientId;
    String clientSecrect;
    String urlWebhook;
    String keyAPI;



    @OneToMany(mappedBy = "providerEntity")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<OrderEntity> orders;

    public String getKeyAPI() {
        return keyAPI;
    }

    public void setKeyAPI(String keyAPI) {
        this.keyAPI = keyAPI;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
