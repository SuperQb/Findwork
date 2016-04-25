package com.qb.findwork.data;

/**
 * Created by QB on 2016/4/25.
 */
public class Accon {
    private String phone;

    public Accon(String phone, String key) {
        this.phone = phone;
        this.key = key;
    }

    public String getPhone() {
        return phone;

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;
}
