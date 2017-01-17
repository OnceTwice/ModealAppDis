package com.ff.modealappdis.myapp.core.service;

import com.ff.modealappdis.myapp.core.domain.User;
import com.ff.modealappdis.network.JSONResult;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.List;

public class UserWriteService {

    public List<User> fetchUserList(String name, String id, String password) {
        String url = "http://192.168.1.26:8088/myapp-api/api/user/input";
        HttpRequest httpRequest = HttpRequest.get(url);

        httpRequest.contentType(HttpRequest.CONTENT_TYPE_FORM);     // 전달 타입
        httpRequest.accept(httpRequest.CONTENT_TYPE_JSON);          // 받을 타입
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        int responseCode = httpRequest.send("name="+name+"&id="+id+"&password="+password).code();

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
