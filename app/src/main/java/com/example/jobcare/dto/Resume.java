package com.example.jobcare.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Resume extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private int age;
    private String title;
    private String phone;
    private String jobs;
    private String canjobs;
    private String introduce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getCanjobs() {
        return canjobs;
    }

    public void setCanjobs(String canjobs) {
        this.canjobs = canjobs;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                ", jobs='" + jobs + '\'' +
                ", canjobs='" + canjobs + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}
