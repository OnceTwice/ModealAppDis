package com.ff.modealappdis.myapp.core.service;

import com.ff.modealappdis.myapp.core.domain.UserVo;
import com.ff.modealappdis.network.JSONResult;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.List;

public class UserReadService {
    public List<UserVo> fetchUserList() {
        String url = "http://192.168.1.26:8088/modeal/user/app/list";
        HttpRequest httpRequest = HttpRequest.get(url);

        httpRequest.contentType(HttpRequest.CONTENT_TYPE_FORM);     // 전달 타입
        httpRequest.accept(httpRequest.CONTENT_TYPE_JSON);          // 받을 타입
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

//        Log.d("11", "111111111111111111111");
        int responseCode = httpRequest.code();
//        Log.d("22", "222222222222222222222");

        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP Response : " + responseCode);
        }
//        Log.d("33", "33333333333333333333333");

        UserReadService.JSONResultUserList jsonResult = fromJSON(httpRequest, UserReadService.JSONResultUserList.class);
//        Log.d("44", "444444444444444444444444");

//        System.out.println(jsonResult.getData());

        return jsonResult.getData();        // DataT vs List<User>
    }

    private class JSONResultUserList extends JSONResult<List<UserVo>> {

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
