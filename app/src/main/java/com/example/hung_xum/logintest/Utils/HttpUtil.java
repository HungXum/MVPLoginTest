package com.example.hung_xum.logintest.Utils;

import android.util.Log;

import com.example.hung_xum.logintest.URL.URLs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Hung_Xum on 2016/4/2.
 */
public class HttpUtil {

    public static String doPost(final String url, final String content) throws Exception {
        try {

            URL httpUrl = new URL(URLs.SERVLET_URL+url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setDoOutput(true);

            //比Get方式多了流输出
            OutputStream out = conn.getOutputStream();
            out.write(content.getBytes());
            //读取服务器返回的信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }

            Log.d("response", sb.toString());
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGet(final String url){
        try {
            URL httpurl = new URL(URLs.SERVLET_URL + url);
            HttpURLConnection conn = (HttpURLConnection) httpurl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine()) != null){
                sb.append(str);
            }
            Log.d("json",sb.toString());

            //将来自web服务器的由图片的二进制码编码成String进行解码成byte[],再写进存储SD卡
//            byte[] b = Base64.decode(sb.toString(),Base64.DEFAULT);
//            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"picture.png");
//            RandomAccessFile accessFile = new RandomAccessFile(file,"rwd");
//            accessFile.write(b);
//            FileOutputStream out = new FileOutputStream(file);
//            out.write(b);
//            out.flush();
//            out.close();

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
