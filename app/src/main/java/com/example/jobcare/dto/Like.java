package com.example.jobcare.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Like extends RealmObject {

    /*
    * 공고 즐겨 찾기
    * */

    @PrimaryKey
    private int id_cp;

    private int notice_idx; // 공고 번호
    private String member_email;

    public int getId_cp() {
        return id_cp;
    }

    public void setId_cp(int id_cp) {
        this.id_cp = id_cp;
    }

    public int getNotice_idx() {
        return notice_idx;
    }

    public void setNotice_idx(int notice_idx) {
        this.notice_idx = notice_idx;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id_cp=" + id_cp +
                ", notice_idx=" + notice_idx +
                ", member_email='" + member_email + '\'' +
                '}';
    }
}
