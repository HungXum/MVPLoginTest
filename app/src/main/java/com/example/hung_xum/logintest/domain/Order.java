package com.example.hung_xum.logintest.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hung_Xum on 2016/6/26.
 */
public class Order {

    private Set<OrderDetail> orderDetail = new HashSet<>();
    private String Price;//总价
    private String date;//日期

    private String Cid;//用户id
    private String address;//地址
    private String receiverPhone;//电话号码

    public Set<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(Set<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
