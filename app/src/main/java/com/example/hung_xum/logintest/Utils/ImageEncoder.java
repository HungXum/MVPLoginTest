package com.example.hung_xum.logintest.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Hung_Xum on 2016/4/22.
 */
public class ImageEncoder {
    //将字节数组的图片信息编码成字符串
    public static String encode(byte[] bytes){
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }
    //将字符串的图片信息解码成字节数组
    public static byte[] decode(String string){
        return Base64.decode(string, Base64.DEFAULT);
    }


    /*
     *@imagePath 图片文件的路径
     */
    //压缩算法，输入图片的路径，将图片进行压缩并编码成String
    //该算法压缩得到的图片，几十k的图片会变成几百k，但是几M的图片会变小成几百k到1M不等
    public static String getImageString(String imagePath){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        //只解码边界，获取边界的长度宽度像素，以便计算下面的压缩率
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

        final int width = options.outWidth;
        final int height = options.outHeight;

        float targetWidth = 480f;
        float targetHeight = 800f;

        int inSampleSize = 1;
        if(width > targetWidth || height > targetHeight){
            final int heightRatio = Math.round((float)height/targetHeight);
            final int widthRatio = Math.round((float)width/targetWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        if (inSampleSize <= 0){
            inSampleSize = 1;
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        //根据options设置和图片路径读取图片
        Bitmap bitmapCompress = BitmapFactory.decodeFile(imagePath,options);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //再次压缩
        bitmapCompress.compress(Bitmap.CompressFormat.PNG,40,out);
        byte[] bytes = out.toByteArray();

        return encode(bytes);
    }
}
