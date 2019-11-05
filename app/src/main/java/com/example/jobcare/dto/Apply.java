package com.example.jobcare.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Apply extends RealmObject {

    @PrimaryKey
    private int idx;
    private String businessId; // 사업자 id
    private String applyId; // 지원자 id
    private int notice_idx; // 공고 idx;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public int getNotice_idx() {
        return notice_idx;
    }

    public void setNotice_idx(int notice_idx) {
        this.notice_idx = notice_idx;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "idx=" + idx +
                ", businessId='" + businessId + '\'' +
                ", applyId='" + applyId + '\'' +
                ", notice_idx=" + notice_idx +
                '}';
    }
}
