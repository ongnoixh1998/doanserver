package com.vanhoang.demo.baokim.model;

public class Bank {
    Integer id;
    String name;
    String short_name;
    String logo;
    Integer lb_available;

    public Bank() {
    }

    public Bank(Integer id, String name, String short_name, String logo, Integer lb_available) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.logo = logo;
        this.lb_available = lb_available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getLb_available() {
        return lb_available;
    }

    public void setLb_available(Integer lb_available) {
        this.lb_available = lb_available;
    }
}
