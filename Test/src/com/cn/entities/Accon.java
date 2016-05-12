package com.cn.entities;

/**
 * Created by QB on 2016/4/25.
 */
public class Accon {
    private String phone;
    private String key;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public Accon(String phone, String key) {
		super();
		this.phone = phone;
		this.key = key;
	}

	public void setKey(String key) {
        this.key = key;
    }

    
}
