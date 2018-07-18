package com.conduit.plastic.request;

import com.conduit.plastic.util.MD5;

/**
 * Created by android on 2017/3/27.
 */

public class UserRequest {
    private String mobilePhone;
    private String password;
    private String nPassword;
    private String valicode;
    private String userName;
    private String jpushId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return MD5.MD5Encode(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getnPassword() {
        return MD5.MD5Encode(nPassword);
    }

    public void setnPassword(String nPassword) {
        this.nPassword = nPassword;
    }

    public String getValicode() {
        return valicode;
    }

    public void setValicode(String valicode) {
        this.valicode = valicode;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }
}
