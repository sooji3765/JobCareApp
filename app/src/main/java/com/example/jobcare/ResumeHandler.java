package com.example.jobcare;

import android.util.Log;
import com.example.jobcare.dto.Resume;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.util.ArrayList;

public class ResumeHandler {

    Realm realm = Realm.getDefaultInstance();

    public void setResume(Resume resume){
        realm.beginTransaction();
        Resume resumes = realm.copyToRealm(resume);
        realm.commitTransaction();
    }

    public ArrayList<Resume> getResume(String id){

        RealmQuery<Resume> query = realm.where(Resume.class);
        // 질의 조건을 추가합니다
        query.equalTo("id", id);
        RealmResults<Resume> resumes = query.findAll();
        ArrayList<Resume> list = new ArrayList<>();

        for (int i=0;i<resumes.size();i++)
            list.add(resumes.get(i));
        return list;
    }

    public Resume oneResume(String id){
        Resume result = realm.where(Resume.class)
                .equalTo("id",id)
                .findFirst();
        return result;
    }

    public void deleteItem(String id){

        Log.d("ID ======",id);
        final Resume result = realm.where(Resume.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
    }
}
