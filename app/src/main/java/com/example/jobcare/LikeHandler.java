package com.example.jobcare;

import android.util.Log;
import com.example.jobcare.dto.Like;
import com.example.jobcare.dto.Notice;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;

public class LikeHandler {

    Realm realm = Realm.getDefaultInstance();

    // 즐겨 찾기 추가
    public void addLike(Like like){
        realm.beginTransaction();
        Like like1 = realm.copyToRealm(like);
        realm.commitTransaction();
    }

    public int getNextKey() {
        int key;
        try {
            Number maxId = realm.where(Like.class).max("id_cp");
            Log.d("MAXID======:",maxId+"");
            key = (maxId == null) ? 1 : maxId.intValue() + 1;
        } catch(ArrayIndexOutOfBoundsException ex) {
            key = 0;
        }
        return key;
    }


    // 즐겨 찾기 조회
    public boolean getLike(int idx, String email){

        Like like = realm.where(Like.class).equalTo("member_email",email)
                .equalTo("notice_idx",idx).findFirst();

        return like==null?false:true;
    }

    public ArrayList<Notice> allLike(String email){
        RealmResults<Like> list = realm.where(Like.class)
                .equalTo("member_email",email)
                .findAll();


        ArrayList<Notice> notices = new ArrayList<Notice>();

        for (int i=0;i<list.size();i++){
            int idx = list.get(i).getNotice_idx();

            Notice notice = realm.where(Notice.class).equalTo("id_cp",idx).findFirst();
            notices.add(notice);
        }

        return notices;
    }

    public void deleteLike(int idx, String email){
        final Like results = realm.where(Like.class)
                .equalTo("notice_idx",idx)
                .equalTo("member_email",email)
                .findFirst();

        Log.d("DELETE====>",results.toString());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteFromRealm();
            }
        });
    }

}
