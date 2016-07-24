package com.example.hung_xum.logintest.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.hung_xum.logintest.Content.UserName;
import com.example.hung_xum.logintest.R;
import com.example.hung_xum.logintest.Thread.FindAllThread;
import com.example.hung_xum.logintest.URL.URLs;
import com.example.hung_xum.logintest.Utils.ImageEncoder;
import com.example.hung_xum.logintest.Utils.UriFileUtil;
import com.example.hung_xum.logintest.adapter.JSONAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private static final int OPEN_PHOTO_FOLDER_REQUEST_CODE = 5001;
    private String imagePath;
    //进度圈
    private ProgressDialog progressDialog;
    private Button orderBtn;
    private Button setPhotoBtn;

    private ListView list;
    private JSONAdapter jsonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();

        orderBtn.setOnClickListener(this);
        setPhotoBtn.setOnClickListener(this);

//        jsonAdapter = new JSONAdapter(HomePage.this);
//
//        new FindAllThread("findalluser.jsp",list,jsonAdapter,new Handler()).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null){
            return;
        }else{
            Uri imageUri = data.getData();
            switch (requestCode){
                case OPEN_PHOTO_FOLDER_REQUEST_CODE:
                    //获取图片的具体路径
                    imagePath = UriFileUtil.getUriPath(HomePage.this, imageUri);
                    //进行异步操作
                    ImageCompressAsyncTask asyncTask = new ImageCompressAsyncTask();
                    asyncTask.execute(imagePath);
                    break;
                default:
                    break;
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_order:
                Intent orderIntent = new Intent(HomePage.this,Order.class);
                startActivity(orderIntent);
                break;
            case R.id.btn_change_profile_photo:
                //跳转到照片选择器
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,OPEN_PHOTO_FOLDER_REQUEST_CODE);
                break;
        }
    }


    //图片的压缩和上传异步操作
    private class ImageCompressAsyncTask extends AsyncTask<String,Void,Void>{

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(HomePage.this);
            progressDialog.setMessage("正在设置中");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            String imageFilePath = params[0];

            //获取图片的实际路径
            String image = ImageEncoder.getImageString(imageFilePath);

            //上传图片编码成的字符串，和用户名，以便更新数据库的user的imageName
            RequestParams requestparams = new RequestParams();
            requestparams.put("photo",image);
            requestparams.put("photoName",UserName.getName());


            String url = URLs.SERVLET_URL + "setprofilephoto.jsp";
            //利用第三方包AsyncHttpClient进行网络传输
            AsyncHttpClient client = new AsyncHttpClient();
            client.post(url,requestparams,new AsyncHttpResponseHandler(){});
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
        }
    }

    /**
     * 绑定各个控件
     */
    public void initView(){
        list = (ListView) findViewById(R.id.list);

        orderBtn = (Button) findViewById(R.id.btn_order);
        setPhotoBtn = (Button) findViewById(R.id.btn_change_profile_photo);
    }

}
