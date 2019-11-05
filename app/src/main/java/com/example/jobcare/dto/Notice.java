package com.example.jobcare.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Notice extends RealmObject {

    @PrimaryKey
    private int id_cp;

    private String id;
    private String businessName;
    private String businessAddress;
    private String businessNumber;
    private String title;
    private String needCnt;
    private String lastDate;
    private String job;
    private String pay;
    private String workPeriod;
    private String workDay;
    private String workTime;
    private String workLocation;
    private String needDo;
    private String workDetail;


    public int getId_cp() {
        return id_cp;
    }

    public void setId_cp(int id_cp) {
        this.id_cp = id_cp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNeedCnt() {
        return needCnt;
    }

    public void setNeedCnt(String needCnt) {
        this.needCnt = needCnt;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(String workPeriod) {
        this.workPeriod = workPeriod;
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getNeedDo() {
        return needDo;
    }

    public void setNeedDo(String needDo) {
        this.needDo = needDo;
    }

    public String getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(String workDetail) {
        this.workDetail = workDetail;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id_cp=" + id_cp +
                ", id='" + id + '\'' +
                ", businessName='" + businessName + '\'' +
                ", businessAddress='" + businessAddress + '\'' +
                ", businessNumber='" + businessNumber + '\'' +
                ", title='" + title + '\'' +
                ", needCnt='" + needCnt + '\'' +
                ", lastDate='" + lastDate + '\'' +
                ", job='" + job + '\'' +
                ", pay='" + pay + '\'' +
                ", workPeriod='" + workPeriod + '\'' +
                ", workDay='" + workDay + '\'' +
                ", workTime='" + workTime + '\'' +
                ", workLocation='" + workLocation + '\'' +
                ", needDo='" + needDo + '\'' +
                ", workDetail='" + workDetail + '\'' +
                '}';
    }
}
