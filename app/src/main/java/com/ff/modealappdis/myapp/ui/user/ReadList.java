package com.ff.modealappdis.myapp.ui.user;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.core.domain.UserVo;
import com.ff.modealappdis.myapp.core.service.UserReadService;
import com.ff.modealappdis.network.SafeAsyncTask;

import java.util.List;

public class ReadList extends ListActivity {
    private UserVoListArrayAdapter userVoListArrayAdapter = null;
    private UserReadService userReadService = new UserReadService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        userVoListArrayAdapter = new UserVoListArrayAdapter(this);
        setListAdapter(userVoListArrayAdapter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.uservo_list);

        findViewById(R.id.user_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadList.this, UserModify.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnListRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchUserListAsyncTask().execute();
            }
        });

//        new FetchUserListAsyncTask().execute();

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
        protected void onSuccess(List<UserVo> userVo) throws Exception {
            userVoListArrayAdapter.add(userVo);         // web으로부터 넘어온 데이터를 추가
//            super.onSuccess(userVos);
        }
    }
}
