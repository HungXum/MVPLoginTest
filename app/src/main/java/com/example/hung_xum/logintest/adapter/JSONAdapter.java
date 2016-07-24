package com.example.hung_xum.logintest.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hung_xum.logintest.R;
import com.example.hung_xum.logintest.Thread.HttpImage;
import com.example.hung_xum.logintest.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hung_Xum on 2016/4/10.
 */
public class JSONAdapter extends BaseAdapter {
    private List<User> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    private Handler handler = new Handler();

    public JSONAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<User> list) {
        Log.d("adapter","setList()");
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.d("adapter","getCount()");
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("adapter","getItem()");
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d("adapter","getItemId()");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("adapter","getView()");
        Hodler hodler = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_list,null);
            hodler = new Hodler(convertView);
            convertView.setTag(hodler);
        }else{
            hodler = (Hodler) convertView.getTag();
        }

        User user = list.get(position);
        hodler.text_name.setText(user.getName());
        hodler.text_password.setText(user.getPassword());
        new HttpImage(hodler.imageView,user.getImageName(),handler).start();
        return convertView;
    }

    class Hodler{
        private ImageView imageView;
        private TextView text_name;
        private TextView text_password;

        public Hodler(View view){
            imageView = (ImageView) view.findViewById(R.id.image);
            text_name = (TextView) view.findViewById(R.id.text_name);
            text_password = (TextView) view.findViewById(R.id.text_password);
        }
    }
}
