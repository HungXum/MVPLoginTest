package com.example.hung_xum.logintest.Thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import com.example.hung_xum.logintest.URL.URLs;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Hung_Xum on 2016/4/11.
 */
public class HttpImage extends Thread {

    private ImageView imageView;
    private String url;
    private Handler handler;

    public HttpImage(ImageView imageView,String url,Handler handler){
        this.imageView = imageView;
        this.url = url;
        this.handler = handler;
    }

    public void doGet(){
        try {
//            Log.d("image", URLs.IMAGE_URL + url);
            //JSONAdapter中调用的该线程进行图片的下载，url为图片在web服务器中的地址
            URL httpUrl = new URL(URLs.IMAGE_URL + url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(conn.getInputStream());
            final Bitmap bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });
            bufferedInputStream.close();
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doGet();
    }
}
