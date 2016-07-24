package com.example.hung_xum.logintest.domain;

/**
 * Created by Hung_Xum on 2016/6/26.
 */
public class OrderDetail {
    private String Pid;
    private String PName;
    private String Num;

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }
}
