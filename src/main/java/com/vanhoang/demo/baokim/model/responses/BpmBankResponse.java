package com.vanhoang.demo.baokim.model.responses;



import com.vanhoang.demo.baokim.model.BpmBank;

import java.util.List;

public class BpmBankResponse {
    Integer code;
    List<String> message;
    Integer count;
    List<BpmBank> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BpmBank> getData() {
        return data;
    }

    public void setData(List<BpmBank> data) {
        this.data = data;
    }
}
