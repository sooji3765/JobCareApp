package com.example.jobcare;

import android.util.Log;
import com.example.jobcare.dto.Notice;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.util.ArrayList;

public class NoticeHandler {

    Realm realm = Realm.getDefaultInstance();
    ApplyHandler applyHandler = new ApplyHandler();

    public void setNotice(Notice notice){
        realm.beginTransaction();
        Notice notice1 = realm.copyToRealm(notice);
        realm.commitTransaction();
    }

    public ArrayList<Notice> getAll(){

        RealmResults<Notice> notices = realm.where(Notice.class).findAll();
        ArrayList<Notice> list = new ArrayList<>();

        for (int i=0;i<notices.size();i++)
            list.add(notices.get(i));

        return list;
    }

    // 지역, 잡 기준 조회
    public ArrayList<Notice> getAll(String[] location, String job){
        RealmResults<Notice> notices = realm.where(Notice.class)
                .in("workLocation",location)
                .contains("job",job)
                .findAll();

        ArrayList<Notice> list = new ArrayList<>();

        for (int i=0;i<notices.size();i++)
            list.add(notices.get(i));

        return list;
    }


    // 지역 기준
    public ArrayList<Notice> getAll(String[] location){
        RealmResults<Notice> notices = realm.where(Notice.class)
                .in("workLocation",location)
                .findAll();

        ArrayList<Notice> list = new ArrayList<>();

        for (int i=0;i<notices.size();i++)
            list.add(notices.get(i));

        return list;
    }

    // 잡 기준
    public ArrayList<Notice> getAll(String job, int idx){
        RealmResults<Notice> notices = realm.where(Notice.class)
                .contains("job",job)
                .findAll();

        ArrayList<Notice> list = new ArrayList<>();

        for (int i=0;i<notices.size();i++)
            list.add(notices.get(i));

        return list;
    }


    public ArrayList<Notice> getNotice(String id){

        RealmQuery<Notice> query = realm.where(Notice.class);
        // 질의 조건을 추가합니다
        query.equalTo("id", id);
        RealmResults<Notice> notices = query.findAll();
        ArrayList<Notice> list = new ArrayList<>();

        for (int i=0;i<notices.size();i++)
            list.add(notices.get(i));
        //Log.d("로그인 결과", notices.get(0).toString());

        return list;
    }

    public int getNextKey() {
        int key;
        try {
            Number maxId = realm.where(Notice.class).max("id_cp");
            Log.d("MAXID======:",maxId+"");
            key = (maxId == null) ? 1 : maxId.intValue() + 1;
        } catch(ArrayIndexOutOfBoundsException ex) {
            key = 0;
        }
        return key;
    }


    // 공고 지우기
    public boolean deleteItem(int id_cp){

        Log.d("ID ======",id_cp+"");
        final Notice result = realm.where(Notice.class).equalTo("id_cp",id_cp).findFirst();

        if (applyHandler.isApply(id_cp)==null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    result.deleteFromRealm();
                }
            });
            return true;
        }
        return false;
    }

    public Notice getItem(int idx){
        return realm.where(Notice.class).equalTo("id_cp",idx).findFirst();
    }
}
