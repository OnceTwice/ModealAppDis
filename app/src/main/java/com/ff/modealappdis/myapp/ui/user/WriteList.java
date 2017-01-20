package com.ff.modealappdis.myapp.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.core.domain.User;
import com.ff.modealappdis.myapp.core.service.UserWriteService;
import com.ff.modealappdis.network.SafeAsyncTask;

import java.util.List;

public class WriteList extends AppCompatActivity {
    private UserWriteService userService = new UserWriteService();
    String id = "";
    String password = "";
    String gender = "";
    String city = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_list);

        /********   id입력    ********/
        EditText editText2 = (EditText)findViewById(R.id.id);
        id = editText2.getText().toString();

        /********   password입력    ********/
        EditText editText3 = (EditText)findViewById(R.id.password);
        password = editText3.getText().toString();

        /********   성별입력    ********/
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroupGender);
        RadioButton man = (RadioButton) findViewById(R.id.radio_man);
        RadioButton woman = (RadioButton) findViewById(R.id.radio_woman);

        if(man.isChecked()) {
            gender = "man";
//            Log.d("젠더젠더12121212", gender);
        }
        else if(woman.isChecked()) {
            gender = "woman";
//            Log.d("젠더젠더13131313", gender);
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_man :
                        gender = "man";
//                            Log.d("젠더젠더", gender);
                        break;
                    case R.id.radio_woman :
                        gender = "woman";
//                            Log.d("젠더젠더", gender);
                        break;
                }
            }
        });

        /********   도시입력    ********/
        ((Spinner)findViewById(R.id.city)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("------>", "onNothingSelected");
            }
        });

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
            Log.d("id : ", id);
            Log.d("password : ", password);
            Log.d("gender ", gender);
            Log.d("city", city);

            List<User> list = userService.fetchUserList(id, password, gender);

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
