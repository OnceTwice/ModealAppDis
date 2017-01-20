package com.ff.modealappdis.myapp.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

    private EditText etID;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private RadioGroup group;
    private RadioButton man;
    private RadioButton woman;
    private Spinner spinnerCity;
    private Button btnSubmit;
    private Button btnCancel;

    String id = "";
    String password = "";
    String gender = "";
    String city = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_list);

        etID = (EditText)findViewById(R.id.id);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText)findViewById(R.id.etPasswordConfirm) ;
        group = (RadioGroup) findViewById(R.id.radioGroupGender);
        man = (RadioButton) findViewById(R.id.radio_man);
        woman = (RadioButton) findViewById(R.id.radio_woman);
        spinnerCity = (Spinner)findViewById(R.id.city);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnCancel = (Button)findViewById(R.id.btnCancel);


        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordConfirm.getText().toString();

//                if( password.equals(confirm) ) {
//                    etPassword.setBackgroundColor(Color.GREEN);
//                    etPasswordConfirm.setBackgroundColor(Color.GREEN);
//                } else {
//                    etPassword.setBackgroundColor(Color.RED);
//                    etPasswordConfirm.setBackgroundColor(Color.RED);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /********  성별입력(최초선택)    ********/
        if(man.isChecked()) {
            gender = "man";
//            Log.d("젠더젠더12121212", gender);
        }
        else if(woman.isChecked()) {
            gender = "woman";
//            Log.d("젠더젠더13131313", gender);
        }

        /********  성별입력(변경)    ********/
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
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                city = (String)adapterView.getItemAtPosition(position);
//                Log.d("------>city", city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("------>", "onNothingSelected");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이디 입력 확인
                if( etID.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteList.this, "ID를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etID.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if( etPassword.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteList.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if( etPasswordConfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteList.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if( !etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) ) {
                    Toast.makeText(WriteList.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }

                new FetchUserListAsyncTask().execute();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private class FetchUserListAsyncTask extends SafeAsyncTask<List<User>> {

        @Override
        public List<User> call() throws Exception {
            Log.d("id : ", etID.getText().toString());
            Log.d("password : ", etPassword.getText().toString());
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
