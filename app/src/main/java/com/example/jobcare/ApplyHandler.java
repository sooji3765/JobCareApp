package com.example.jobcare;

import android.util.Log;
import com.example.jobcare.dto.Apply;
import com.example.jobcare.dto.Member;
import com.example.jobcare.dto.Notice;
import com.example.jobcare.dto.Resume;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;

public class ApplyHandler {
    Realm realm = Realm.getDefaultInstance();

    // 지원하기
    public void addApply(Apply apply){
        realm.beginTransaction();
        Apply item = realm.copyToRealm(apply);
        realm.commitTransaction();
    }


    // 비지니스 기준 지원자 조회
    public ArrayList<Resume> getApplier(String businessEmail){

        RealmResults<Apply> list = realm.where(Apply.class)
                .equalTo("businessId",businessEmail)
                .findAll();

        ArrayList<Resume> result = new ArrayList<>();

        for (Apply item : list){
            Resume items = realm.where(Resume.class)
                    .equalTo("id",item.getApplyId())
                    .findFirst();
            result.add(items);
        }

        return result;
    }

    public Apply isApply(String applyId, int notice_idx){
        Apply result = realm.where(Apply.class)
                .equalTo("applyId",applyId)
                .equalTo("notice_idx",notice_idx)
                .findFirst();

        return result;
    }

    public Apply isApply(int notice_idx){
        Apply result = realm.where(Apply.class)
                .equalTo("notice_idx",notice_idx)
                .findFirst();

        return result;
    }

    // 지원한 목록
    public ArrayList<Notice> getNotice(String userEmail){
        RealmResults<Apply> list = realm.where(Apply.class)
                .equalTo("applyId",userEmail)
                .findAll();

        ArrayList<Notice> result = new ArrayList<>();

        for (Apply item : list){
            Notice notice = realm.where(Notice.class)
                    .equalTo("id_cp",item.getNotice_idx())
                    .findFirst();

            result.add(notice);
        }

        return result;
    }


    // idx 조회
    public int getNextKey() {
        int key;
        try {
            Number maxId = realm.where(Apply.class).max("idx");
            Log.d("MAXID======:",maxId+"");
            key = (maxId == null) ? 1 : maxId.intValue() + 1;
        } catch(ArrayIndexOutOfBoundsException ex) {
            key = 0;
        }
        return key;
    }

    public void deleteItem(int notice_idx, String applyId){
        Log.d("ID ======",notice_idx+"");
        final Apply result = realm.where(Apply.class)
                .equalTo("notice_idx",notice_idx)
                .equalTo("applyId",applyId)
                .findFirst();

        realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    result.deleteFromRealm();
                }
            });

    }

    public Apply isEmail(String id) {
        Apply result = realm.where(Apply.class)
                .equalTo("applyId",id)
                .findFirst();

        return result;

    }
}
