package com.ff.modealappdis.test;

import com.ff.modealappdis.network.JSONResult;
import com.ff.modealappdis.vo.UserVo;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.List;

public class UserServiceTest {
    public List<UserVo> fetchUserList() {
        String url = "http://192.168.1.26:8088/modeal/user/app/list";

        // HttpRequest httpRequest = HttpRequest.get(url);
        // httpRequest.contentType( HttpRequest.CONTENT_TYPE_JSON );   // get방식(전달타입)

        HttpRequest httpRequest = HttpRequest.post(url);
        httpRequest.contentType(HttpRequest.CONTENT_TYPE_FORM);     // post방식(전달 타입)
        httpRequest.accept(httpRequest.CONTENT_TYPE_JSON);          // 받을 타입
        httpRequest.connectTimeout(3000);
        httpRequest.readTimeout(3000);

        int responseCode = httpRequest.code();

        if(responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP Response : " + responseCode);
        }

        JSONResultUserList jsonResult = fromJSON(httpRequest, JSONResultUserList.class);

        return jsonResult.getData();        // DataT vs List<User>
    }

    private class JSONResultUserList extends JSONResult<List<UserVo>> {

    }

    // JSON 문자열을 자바 객체로 변환
    protected <V> V fromJSON(HttpRequest request, Class<V> target ) {
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
