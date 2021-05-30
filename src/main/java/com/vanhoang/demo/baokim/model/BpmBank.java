package com.vanhoang.demo.baokim.model;

public class BpmBank {
    Integer id;
    String name;
    Integer bank_id;
    Integer type;
    String complete_time;
    String bank_name;
    String bank_short_name;
    String bank_logo;

    public BpmBank() {
    }

    public BpmBank(Integer id, String name, Integer bank_id, Integer type, String complete_time, String bank_name, String bank_short_name, String bank_logo) {
        this.id = id;
        this.name = name;
        this.bank_id = bank_id;
        this.type = type;
        this.complete_time = complete_time;
        this.bank_name = bank_name;
        this.bank_short_name = bank_short_name;
        this.bank_logo = bank_logo;
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

    public Integer getBank_id() {
        return bank_id;
    }

    public void setBank_id(Integer bank_id) {
        this.bank_id = bank_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(String complete_time) {
        this.complete_time = complete_time;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_short_name() {
        return bank_short_name;
    }

    public void setBank_short_name(String bank_short_name) {
        this.bank_short_name = bank_short_name;
    }

    public String getBank_logo() {
        return bank_logo;
    }

    public void setBank_logo(String bank_logo) {
        this.bank_logo = bank_logo;
    }
}
