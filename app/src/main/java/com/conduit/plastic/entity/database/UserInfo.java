package com.conduit.plastic.entity.database;

import android.text.TextUtils;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by android on 2017/3/21.
 */

//public class UserInfo extends DataSupport {
public class UserInfo implements Serializable {
    //
// "uid":"402880ed5b0dbe04015b0dc2b2020000",
//         "mobilePhone":"13548143525",
//         "headImage":null,
//         "license":null,
//         "areaName":"",
//         "companyName":null,
//         "companyType":null,
//         "contacts":null,
//         "contactNumber":null,
//         "officePhone":null,
//         "qq":null,
//         "brandNames":null,
//         "email":null,
//         "userType":20,
//         "qqAccount":"1356555666",
//         "userCode":10001,
//         "sex":0,
//         "idCardNum":"5116021994121109",
//         "wxAccount":"ghh",
//         "wbAccount":null,
//         "onlineStatus":1,
//         "lastLoginTime":"2017-05-04 15:07:35",
//         "position":null,
//         "userName":"13548143525",
//         "address":null,
//         "id":"402880ed5b0dbe04015b0dc2b2020000"
//    @Column(ignore = false, unique = true, nullable = false)
    private String uid;
    private String sk;
    private String userName;
    private String position;
    private int status;
    private  int loginCount=0;
    private String password;//密码
    private int delFlag;
    private String jpushId;//激光推送编号
    private String memo;//备注
    private String wxAccount;//微信账号
    private String userCode;
    private String disturbStatus;
    private String relHeadImage;
    private int userType;
    private String idCardNum;//身份证号码
    private String birth;
    private String email;//邮箱
    private String qqAccount;//QQ账号
    private String wbAccount;//微博账号
    private int lockStatus;//锁定状态
    private String headImage;
    private String sex;
    private int onlineStatus;
    private String age;
    private String lastLoginTime;
    private String registerTime;
    private String mobilePhone;//手机号（唯一 mobile）
    private boolean isLogin = false;
    private String license;//营业执照
    private String areaName;//所在区域
    private String companyName;//公司名称
    private String contacts;//联系人
    private String contactNumber;//手机
    private String brandNames;//经营品牌
    private String officePhone;//座机
    private String qq;//联系QQ
    private String companyType;//公司类别
    private String address;//地址
    String headNameStr = "http://upload5.51yasai.com/attached/image/20130705/20130705093652_49957.jpg";



    public boolean isEditUser(){

//        if (getLoginCount()<=4){
//
//        }else {
//
//        }
        return getLoginCount()>4&&TextUtils.isEmpty(mobilePhone);
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getWxAccount() {
        return wxAccount;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getDisturbStatus() {
        return disturbStatus;
    }

    public void setDisturbStatus(String disturbStatus) {
        this.disturbStatus = disturbStatus;
    }

    public String getRelHeadImage() {
        return relHeadImage;
    }

    public void setRelHeadImage(String relHeadImage) {
        this.relHeadImage = relHeadImage;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqAccount() {
        return qqAccount;
    }

    public void setQqAccount(String qqAccount) {
        this.qqAccount = qqAccount;
    }

    public String getWbAccount() {
        return wbAccount;
    }

    public void setWbAccount(String wbAccount) {
        this.wbAccount = wbAccount;
    }

    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getHeadImage() {
        if (!TextUtils.isEmpty(headImage))
            return headImage;
        else
            return headNameStr;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(String brandNames) {
        this.brandNames = brandNames;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
