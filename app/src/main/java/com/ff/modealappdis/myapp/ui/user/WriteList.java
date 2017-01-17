package com.ff.modealappdis.myapp.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.core.domain.User;
import com.ff.modealappdis.myapp.core.service.UserWriteService;
import com.ff.modealappdis.network.SafeAsyncTask;

import java.util.List;

public class WriteList extends AppCompatActivity {
    private UserWriteService userService = new UserWriteService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_list);

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchUserListAsyncTask().execute();
            }
        });
    }

    private class FetchUserListAsyncTask extends SafeAsyncTask<List<User>> {
        @Override
        public List<User> call() throws Exception {
            EditText editText1 = (EditText)findViewById(R.id.name);
            Log.d("name : ", editText1.getText().toString());
            String name = editText1.getText().toString();

            EditText editText2 = (EditText)findViewById(R.id.id);
            Log.d("id : ", editText2.getText().toString());
            String id = editText2.getText().toString();

            EditText editText3 = (EditText)findViewById(R.id.password);
            Log.d("password : ", editText3.getText().toString());
            String password = editText3.getText().toString();

            List<User> list = userService.fetchUserList(name, id, password);

            return list;
        }

        @Override
        protected void onException(Exception e) throws RuntimeException {
            // super.onException(e);
            throw new RuntimeException(e);
        }

        @Override
        protected void onSuccess(List<User> users) throws Exception {
            // super.onSuccess(users);

        }
    }
}
