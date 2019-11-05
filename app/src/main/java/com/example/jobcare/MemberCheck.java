package com.example.jobcare;

import android.content.SharedPreferences;
import android.util.Log;
import com.example.jobcare.dto.Member;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MemberCheck {
    Realm realm = Realm.getDefaultInstance();
    final String TAG ="MEMBER CHECK";

    // 유효성 체크
    public boolean memberValid(String email, String password){

        // Build the query looking at all member:
        RealmQuery<Member> query = realm.where(Member.class);

        // 질의 조건을 추가합니다
        query.equalTo("id", email);
        query.equalTo("password",password);

        // 질의를 수행합니다
        RealmResults<Member> result1 = query.findAll();

        Log.d("로그인 결과", result1.toString());
        Log.d("REALM",realm.getPath());
        Log.d("REALM ADDRESS :",realm.getPath());
        if (result1.size()>0)
            return true;
        return false;
    }

    public boolean memberRegister(final Member member){

        Log.d("MEMBER REGISTER :" ,member.toString());

        realm.beginTransaction();
        Member realmUser = realm.copyToRealm(member);
        realm.commitTransaction();

        return true;
    }

    public Member getMember(String id){
        Member member = new Member();

        RealmQuery<Member> query = realm.where(Member.class);

        // 질의 조건을 추가합니다
        query.equalTo("id", id);
        member = query.findFirst();

        Log.d("로그인 결과", member.toString());

        return member;
    }

    public void imgAdd(final byte[] img, final String id){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Member member = realm.where(Member.class)
                        .equalTo("id",id).findFirst();

                member.setImg(img);
                Log.d(TAG,member.toString());
                Log.d(TAG, "update img");
            }
        });

    }

}
