package com.example.hung_xum.logintest.Utils;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by Hung_Xum on 2016/4/10.
 */
public class GsonUtil {
    public static <T> T parseJson(String json,Class<T> clazz){
        T t = null;
        try{
            Gson gson = new Gson();
            gson.toJson(json,clazz);
        }catch (Exception e){
            Log.d("json解析","json解析出错");
        }
        return t;
    }
}
