package com.example.hung_xum.logintest.Thread;

import android.os.Handler;

import com.example.hung_xum.logintest.Utils.HttpUtil;

import org.json.JSONObject;

/**
 * Created by Hung_Xum on 2016/4/2.
 */
public class RegistThread extends Thread {
    private String url;
    private String content;
    private Handler handler;

    public RegistThread(String url,String content,Handler handler){
        this.url = url;
        this.content = content;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            String json = HttpUtil.doPost(url, content);
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.getString("name") != null){
                //注册用户，验证是否该用户名已经存在，若存在发送信息为0x1235的msg给UI线程，进行一个Toast的提示
                handler.sendEmptyMessage(0x1235);
            }else{
                handler.sendEmptyMessage(0x1236);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
