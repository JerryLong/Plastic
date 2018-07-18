package com.conduit.plastic.entity;


import com.conduit.plastic.entity.database.UserInfo;

/**
 * Created by android on 2017/3/10.
 */

public class UserEntity {

    /**
     * sk : 463e273a636cccbe7bed26d4570a22fd8e8364b54651ddbde5661fac89447bb1ab558301bbba438d3e8c22187cd2866d8872e84121e4794678900e24e62d1c21
     * user : {"id":"402880ed5b0dbe04015b0dc2b2020000","userName":"13548143525","status":30,"password":"b4b357ce11be3a86","delFlag":0,"jpushId":"13548143525","memo":null,"wxAccount":null,"userCode":null,"disturbStatus":null,"relHeadImage":null,"userType":10,"idCardNum":null,"birth":null,"email":null,"qqAccount":null,"wbAccount":null,"lockStatus":0,"headImage":null,"sex":0,"onlineStatus":0,"age":null,"lastLoginTime":1490584515068,"registerTime":1490584515068,"mobilePhone":"13548143525"}
     */

    private String sk;
    private UserInfo user;

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }


}
