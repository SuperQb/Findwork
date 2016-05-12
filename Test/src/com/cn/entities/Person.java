package com.cn.entities;

/**
 * Created by QB on 2016/4/25.
 */
public class Person {
    private String registerPhone;
    private String phone;
    private String name;
    private String icon;
    private String age;
    private String sex;

    public Person(String registerPhone, String phone, String name, String icon, String age, String sex) {
        this.registerPhone = registerPhone;
        this.phone = phone;
        this.name = name;
        this.icon = icon;
        this.age = age;
        this.sex = sex;
    }



    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
