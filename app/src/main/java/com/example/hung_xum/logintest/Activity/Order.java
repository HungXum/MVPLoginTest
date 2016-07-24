package com.example.hung_xum.logintest.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hung_xum.logintest.R;
import com.example.hung_xum.logintest.domain.OrderDetail;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class Order extends AppCompatActivity implements View.OnClickListener {

    private EditText cidEt;
    private EditText adressEt;
    private EditText phoneEt;
    private EditText pname1Et;
    private EditText pname2Et;
    private EditText pname3Et;
    private EditText pnum1Et;
    private EditText pnum2Et;
    private EditText pnum3Et;
    private EditText dateEt;

    private TextView priceTxt;

    private Button OrderBtn;

    private com.example.hung_xum.logintest.domain.Order order;
    private OrderDetail detail;
    private String orderJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();

        OrderBtn.setOnClickListener(this);

    }

    public com.example.hung_xum.logintest.domain.Order getOrder(){
        order = new com.example.hung_xum.logintest.domain.Order();
        Set<OrderDetail> setOrders = new HashSet<>();

        order.setCid(cidEt.getText().toString());
        order.setAddress(adressEt.getText().toString());
        order.setReceiverPhone(phoneEt.getText().toString());

        detail = new OrderDetail();
        detail.setPid(pname1Et.getText().toString());
        detail.setNum(pnum1Et.getText().toString());
        setOrders.add(detail);

        detail = new OrderDetail();
        detail.setPid(pname2Et.getText().toString());
        detail.setNum(pnum2Et.getText().toString());
        setOrders.add(detail);

        detail = new OrderDetail();
        detail.setPid(pname3Et.getText().toString());
        detail.setNum(pnum3Et.getText().toString());
        setOrders.add(detail);

        order.setOrderDetail(setOrders);
        order.setPrice("40");
        order.setDate(dateEt.getText().toString());

        return order;
    }

    public void initView(){
        cidEt = (EditText) findViewById(R.id.edit_Cid);
        adressEt = (EditText) findViewById(R.id.edit_address);
        phoneEt = (EditText) findViewById(R.id.edit_phone);
        pname1Et = (EditText) findViewById(R.id.edit_pname1);
        pname2Et = (EditText) findViewById(R.id.edit_pname2);
        pname3Et = (EditText) findViewById(R.id.edit_pname3);
        pnum1Et = (EditText) findViewById(R.id.edit_pnum1);
        pnum2Et = (EditText) findViewById(R.id.edit_pnum2);
        pnum3Et = (EditText) findViewById(R.id.edit_pnum3);
        dateEt = (EditText) findViewById(R.id.edit_date);

        priceTxt = (TextView) findViewById(R.id.txt_price);

        OrderBtn = (Button) findViewById(R.id.btn_commit_order);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_commit_order:
                Gson gson = new Gson();
                orderJson = gson.toJson(getOrder());
                Log.d("orderJson",orderJson);
                break;
        }
    }
}
