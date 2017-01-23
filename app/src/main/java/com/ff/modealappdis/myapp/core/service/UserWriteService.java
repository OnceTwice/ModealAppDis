package com.ff.modealappdis.myapp.core.service;

import android.util.Log;

import com.ff.modealappdis.myapp.core.domain.User;
import com.ff.modealappdis.network.JSONResult;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.List;

public class UserWriteService {

    public List<User> fetchUserList(String id, String password, String gender, String location, String birth, String mkName, String mkAddress, String mkPhoneN, String mkIntroduce) {
        // String url = "http://192.168.1.26:8088/myapp-api/api/user/input";
        // String url = "http://192.168.1.26:8088/modeal/user/app/input";
        // String url = "http://10.10.102.57:8088/modeal/user/app/input";
        String url = "http://192.168.0.4:8088/modeal/user/app/input";
        HttpRequest httpRequest = HttpRequest.get(url);

        httpRequest.contentType(HttpRequest.CONTENT_TYPE_FORM);     // 전달 타입
        httpRequest.accept(httpRequest.CONTENT_TYPE_JSON);          // 받을 타입
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        Log.d("아이디 : ", id);
        Log.d("비밀번호 : ", password);
        Log.d("성별 ", gender);
        Log.d("위치", location);
        Log.d("생년월일 : ", birth);

        Log.d("매장명 : ", mkName);
        Log.d("매장주소 : ", mkAddress);
        Log.d("매장전화번호 : ", mkPhoneN);
        Log.d("매장소개 : ", mkIntroduce);

        int responseCode = httpRequest.send("id="+id+ "&password="+password+ "&gender="+gender
                                        + "&location="+location + "&birth="+birth
                                        + "&mkName="+mkName + "&mkAddress="+mkAddress
                                        + "&mkPhoneN="+mkPhoneN + "&mkIntro="+mkIntroduce).code();

        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP Response : " + responseCode);
        }

        UserWriteService.JSONResultUserList jsonResult = fromJSON(httpRequest, UserWriteService.JSONResultUserList.class);

        return jsonResult.getData();        // DataT vs List<User>
    }

    private class JSONResultUserList extends JSONResult<List<User>> {

    }


    // JSON 문자열을 자바 객체로 변환
    protected <V> V fromJSON( HttpRequest request, Class<V> target ) {
        V v = null;

        try {
            Gson gson = new GsonBuilder().create();         // GSON 인스턴스 생성

            Reader reader = request.bufferedReader();
            v = gson.fromJson(reader, target);              // JSONResultUserList 클래스 객체로 변환
            reader.close();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }

        return v;
    }
}
