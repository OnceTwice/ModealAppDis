package com.ff.modealappdis.myapp.ui.user;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.core.domain.User;
import com.ff.modealappdis.myapp.core.service.UserService;
import com.ff.modealappdis.network.SafeAsyncTask;

import java.util.List;

public class UserList extends ListActivity {
    private UserListArrayAdapter userListArrayAdapter = null;
    private UserService userService = new UserService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userListArrayAdapter = new UserListArrayAdapter(this);
        setListAdapter(userListArrayAdapter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchUserListAsyncTask().execute();
            }
        });
    }

    private class FetchUserListAsyncTask extends SafeAsyncTask<List<User>> {
        @Override
        public List<User> call() throws Exception {
            List<User> list = userService.fetchUserList();
//            System.out.println(list);
            return list;
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            // super.onException(e);
            throw new RuntimeException(e);
        }

        @Override
        protected void onSuccess(List<User> users) throws Exception {
            // 통신 결과를 처리
            userListArrayAdapter.add(users);
            // super.onSuccess(users);
        }
    }
}
