package com.ff.modealappdis.myapp.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.core.domain.UserVo;
import com.ff.modealappdis.myapp.core.service.UserReadService;
import com.ff.modealappdis.network.SafeAsyncTask;

import java.util.List;

public class ReadList extends AppCompatActivity {
    private UserReadService userReadService = new UserReadService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_list);


        findViewById(R.id.user_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadList.this, UserModify.class);
                startActivity(intent);
            }
        });

        new FetchUserListAsyncTask().execute();

//        userReadService.fetchUserList();      // 쓰레드 없이 사용할 경우 에러 발생
    }

    private class FetchUserListAsyncTask extends SafeAsyncTask<List<UserVo>> {
        @Override
        public List<UserVo> call() throws Exception {
            List<UserVo> list = userReadService.fetchUserList();
//            System.out.println(list);
            return list;
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            super.onException(e);
        }

        @Override
        protected void onSuccess(List<UserVo> userVos) throws Exception {
            super.onSuccess(userVos);
        }
    }
}
