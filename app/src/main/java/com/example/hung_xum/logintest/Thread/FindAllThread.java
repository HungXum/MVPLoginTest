package com.example.hung_xum.logintest.Thread;

import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import com.example.hung_xum.logintest.Utils.HttpUtil;
import com.example.hung_xum.logintest.adapter.JSONAdapter;
import com.example.hung_xum.logintest.domain.User;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hung_Xum on 2016/4/10.
 */
public class FindAllThread extends Thread {
    private String url;
    private ListView listView;
    private JSONAdapter adapter;
    private Handler handler;

    private List<User> list;
    private User user;

    public FindAllThread(String url,ListView listView,JSONAdapter adapter,Handler handler){
        this.url = url;
        this.listView = listView;
        this.adapter = adapter;
        this.handler = handler;
    }

    public void doGet(){
        try{
            list = new ArrayList<>();
            //所有已经注册的用户的信息的json数据
            String json_str = HttpUtil.doGet(url);

            JSONArray jsonArray = new JSONArray(json_str);
            for(int i = 0;i < jsonArray.length();i++){
                user = new User();
                user.setName(jsonArray.getJSONObject(i).getString("name"));
                user.setPassword(jsonArray.getJSONObject(i).getString("password"));
                user.setImageName(jsonArray.getJSONObject(i).getString("imageName"));
                list.add(user);
            }

            //更新UI，listview的数据
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter.setList(list);
                    listView.setAdapter(adapter);
                }
            });

        }catch (Exception e){
            Log.d("doGet","json数据转化出错");
        }

    }

    @Override
    public void run() {
        doGet();
    }
}
