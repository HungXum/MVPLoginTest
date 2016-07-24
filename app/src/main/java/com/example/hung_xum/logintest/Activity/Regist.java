package com.example.hung_xum.logintest.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hung_xum.logintest.R;
import com.example.hung_xum.logintest.Thread.RegistThread;

public class Regist extends AppCompatActivity {

    private EditText edit_name,edit_pass;
    private Button btn_regist;
    private Handler handler;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        edit_name = (EditText) findViewById(R.id.regist_name);
        edit_pass = (EditText) findViewById(R.id.regist_password);
        btn_regist = (Button) findViewById(R.id.regist_regist);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 0x1235:
                        Toast.makeText(Regist.this, "该用户名已被注册！", Toast.LENGTH_SHORT).show();
                        break;
                    case 0x1236:
                        Toast.makeText(Regist.this,"注册成功！",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = "regist.jsp";
                String name = edit_name.getText().toString();
                String password = edit_pass.getText().toString();
                String content = "name=" + name + "&password=" + password;
                new RegistThread(url,content,handler).start();
            }
        });
    }
}
