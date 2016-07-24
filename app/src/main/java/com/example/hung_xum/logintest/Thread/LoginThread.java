package com.example.hung_xum.logintest.Thread;

import android.os.Handler;
import android.util.Log;

import com.example.hung_xum.logintest.Content.UserName;
import com.example.hung_xum.logintest.Utils.HttpUtil;

import org.json.JSONObject;

/**
 * Created by Hung_Xum on 2016/4/2.
 */
public class LoginThread extends Thread {
    private String url;
    private String content;
    private Handler handler;

    public LoginThread(String url, String content,Handler handler) {
        this.url = url;
        this.content = content;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            String json = HttpUtil.doPost(url, content);
            JSONObject object = new JSONObject(json);
            Log.d("login",json);
            //Web服务器返回的是User的整个对象，有id,name,password,imageName;
            if(object.getInt("status") == 200){
                //登录成功
                handler.sendEmptyMessage(0x1234);
                //记录登录的username
//                UserName.setName(object.getString("name"));
            }else {
                handler.sendEmptyMessage(0x1233);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
