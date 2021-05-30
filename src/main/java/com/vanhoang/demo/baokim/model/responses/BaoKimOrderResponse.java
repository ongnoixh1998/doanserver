package com.vanhoang.demo.baokim.model.responses;

import java.util.List;
import java.util.Map;

public class BaoKimOrderResponse {
    Integer code;
//    Map message;
    Integer count;
    BaoKimOrderDataResponse data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }



    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BaoKimOrderDataResponse getData() {
        return data;
    }

    public void setData(BaoKimOrderDataResponse data) {
        this.data = data;
    }
}
