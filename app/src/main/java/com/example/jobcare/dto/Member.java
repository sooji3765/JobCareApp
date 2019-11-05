package com.example.jobcare.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.Arrays;

public class Member extends RealmObject {

    @PrimaryKey
    private String id;
    private boolean isBusiness;
    private String password;
    private String name;
    private int age;
    private String phone;
    private String businessName;
    private String businessAddress;
    private String businessNumber;
    private byte[] img;


    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public String getBusinessName() { return businessName; }

    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getBusinessAddress() { return businessAddress; }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", isBusiness=" + isBusiness +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessNumber='" + businessNumber + '\'' +
                ", img=" + Arrays.toString(img) +
                '}';
    }
}
