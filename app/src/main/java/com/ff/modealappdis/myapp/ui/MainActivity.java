package com.ff.modealappdis.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ff.modealappdis.R;
import com.ff.modealappdis.myapp.ui.fragment.MainFragmentActivity;
import com.ff.modealappdis.myapp.ui.user.ReadList;
import com.ff.modealappdis.myapp.ui.user.UserList;
import com.ff.modealappdis.myapp.ui.user.WriteList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserList.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnWrite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WriteList.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnRead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReadList.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btnFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainFragmentActivity.class);
                startActivity(intent);
            }
        });

    }
}
