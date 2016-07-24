package com.example.hung_xum.logintest.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hung_xum.logintest.R;
import com.example.hung_xum.logintest.Thread.LoginThread;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText user_name,user_pass;
    private Button login,regist;
    private String url;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_name = (EditText) findViewById(R.id.user_name);
        user_pass = (EditText) findViewById(R.id.user_password);
        login = (Button) findViewById(R.id.login);
        regist = (Button) findViewById(R.id.regist);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0x1234:
                        Toast.makeText(Login.this, "登陆成功!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,HomePage.class));
                        break;
                    case 0x1233:
                        Toast.makeText(Login.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //登录按钮，将name和password发送到web服务器
            case R.id.login:
                url = "login.jsp";
                String name = user_name.getText().toString();
                String password = user_pass.getText().toString();
                String content = "name=" + name + "&password=" + password;
                try {
                    new LoginThread(url,content,handler).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //注册按钮
            case R.id.regist:
                Intent intent = new Intent(Login.this,Regist.class);
                startActivity(intent);
                break;
        }
    }
}
