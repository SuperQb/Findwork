package com.cn.entities;


public class Work {

    //private int id;
    private String position;
    private String pay;
    private String sex;
    private String location;
    private String intrduce;
    private String content;
    private String requid;
    private String callphone;
    private String phone;
    private String type;
    private String linkman;
//    public int getId() {
//        return id;
//    }

public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	//    public Work(int id, String position, String pay, String sex, String location, String intrduce, String content,
//			String requid, String callphone, String phone, String type) {
//		super();
//		this.id = id;
//		this.position = position;
//		this.pay = pay;
//		this.sex = sex;
//		this.location = location;
//		this.intrduce = intrduce;
//		this.content = content;
//		this.requid = requid;
//		this.callphone = callphone;
//		this.phone = phone;
//		this.type = type;
//	}
    public Work( String position, String pay, String sex, String location, String intrduce, String content,
			String requid, String callphone, String phone, String type ,String linkman) {
		super();
		
		this.position = position;
		this.pay = pay;
		this.sex = sex;
		this.location = location;
		this.intrduce = intrduce;
		this.content = content;
		this.requid = requid;
		this.callphone = callphone;
		this.phone = phone;
		this.type = type;
		this.linkman=linkman;
	}

//	public void setId(int id) {
//        this.id = id;
//    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntrduce() {
        return intrduce;
    }

    public void setIntrduce(String intrduce) {
        this.intrduce = intrduce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequid() {
        return requid;
    }

    public void setRequid(String requid) {
        this.requid = requid;
    }

    public String getCallphone() {
        return callphone;
    }

    public void setCallphone(String callphone) {
        this.callphone = callphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   

}
