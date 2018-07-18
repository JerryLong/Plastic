package com.conduit.plastic.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.conduit.plastic.common.PlasticApp;
import com.conduit.plastic.entity.database.UserInfo;

/**
 * Created by android on 2016/11/17.
 */

public class UserUtils {
    private final static String USER = "user";// xml的文件名
    private final static String USER_INTEGRATION = "use_integrationr";// xml的文件名
    private static UserUtils share;
    private SharedPreferences spf;
    private SharedPreferences.Editor edit;

    private String sk = "sk";//
    private String id = "id";
    private String jpushId = "jpushId";
    private String userName = "userName";
    private String status = "status";
    private String license = "license";
    private String areaName = "areaName";
    private String companyName = "companyName";
    private String companyType = "companyType";
    private String contacts = "contacts";
    private String address = "address";
    private String position = "position";
    private String brandNames = "brandNames";
    private String contactNumber = "contactNumber";
    private String officePhone = "officePhone";
    private String password = "password";
    private String delFlag = "delFlag";
    private String memo = "memo";
    private String wxAccount = "wxAccount";
    private String userCode = "userCode";
    private String disturbStatus = "disturbStatus";
    private String relHeadImage = "relHeadImage";
    private String userType = "userType";
    private String idCardNum = "idCardNum";
    private String birth = "birth";
    private String email = "email";
    private String qqAccount = "qqAccount";
    private String wbAccount = "wbAccount";
    private String lockStatus = "lockStatus";
    private String headImage = "headImage";
    private String sex = "sex";
    private String onlineStatus = "onlineStatus";
    private String age = "age";
    private String lastLoginTime = "lastLoginTime";
    private String registerTime = "registerTime";
    private String mobilePhone = "mobilePhone";
    private int loginType = -1;//-1未登录过，0登录过,1已登录
    private String isLogin = "isLogin";

    /**
     * @return
     */
    public static UserUtils getInstance() {
        if (share == null) {
            synchronized (UserUtils.class) {
                if (share == null) {
                    share = new UserUtils(PlasticApp.getInstance());
                }
            }
        }
        return share;
    }

    private UserUtils(Context context) {
        spf = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        edit = spf.edit();
    }

    public void saveUser(UserInfo bean) {
        if (bean != null) {
            if (!TextUtils.isEmpty(bean.getUid())) {
                edit.putString(id, bean.getUid());
            }
            if (!TextUtils.isEmpty(bean.getUserName())) {
                edit.putString(userName, bean.getUserName());
            }
            if (!TextUtils.isEmpty(bean.getSk())) {
                edit.putString(sk, bean.getSk());
            }
            if (bean.getStatus() != -1) {
                edit.putInt(status, bean.getStatus());
            }
            if (bean.getDelFlag() != -1) {
                edit.putInt(delFlag, bean.getDelFlag());
            }
            if (bean.getUserType() != -1) {
                edit.putInt(userType, bean.getUserType());
            }
            if (bean.getLockStatus() != -1) {
                edit.putInt(lockStatus, bean.getLockStatus());
            }
            if (!TextUtils.isEmpty(bean.getPassword())) {
                edit.putString(password, bean.getPassword());
            }
            if (!TextUtils.isEmpty(bean.getMemo())) {
                edit.putString(memo, bean.getMemo());
            }
            if (!TextUtils.isEmpty(bean.getWxAccount())) {
                edit.putString(wxAccount, bean.getWxAccount());
            }
            if (!TextUtils.isEmpty(bean.getWbAccount())) {
                edit.putString(wbAccount, bean.getWbAccount());
            }
            if (!TextUtils.isEmpty(bean.getQqAccount())) {
                edit.putString(qqAccount, bean.getQqAccount());
            }
            if (!TextUtils.isEmpty(bean.getDisturbStatus())) {
                edit.putString(disturbStatus, bean.getDisturbStatus());
            }
            if (!TextUtils.isEmpty(bean.getIdCardNum())) {
                edit.putString(idCardNum, bean.getIdCardNum());
            }
            if (!TextUtils.isEmpty(bean.getEmail())) {
                edit.putString(email, bean.getEmail());
            }
            if (!TextUtils.isEmpty(bean.getRelHeadImage())) {
                edit.putString(relHeadImage, bean.getRelHeadImage());
            }
            if (!TextUtils.isEmpty(bean.getLastLoginTime())) {
                edit.putString(lastLoginTime, bean.getLastLoginTime());
            }
            if (!TextUtils.isEmpty(bean.getMobilePhone())) {
                edit.putString(mobilePhone, bean.getMobilePhone());
            }
            if (!TextUtils.isEmpty(bean.getRegisterTime())) {
                edit.putString(registerTime, bean.getRegisterTime());
            }
            if (!TextUtils.isEmpty(bean.getHeadImage())) {
                edit.putString(headImage, bean.getHeadImage());
            }
            if (!TextUtils.isEmpty(bean.getUserCode())) {
                edit.putString(userCode, bean.getUserCode());
            }
            if (!TextUtils.isEmpty(bean.getUserCode())) {
                edit.putString(sex, bean.getSex());
            }
            if (!TextUtils.isEmpty(bean.getBirth())) {
                edit.putString(birth, bean.getBirth());
            }
            if (!TextUtils.isEmpty(bean.getAge())) {
                edit.putString(age, bean.getAge());
            }
            if (!TextUtils.isEmpty(bean.getLicense())) {
                edit.putString(license, bean.getLicense());
            }
            if (!TextUtils.isEmpty(bean.getHeadImage())) {
                edit.putString(headImage, bean.getHeadImage());
            }
            if (!TextUtils.isEmpty(bean.getAreaName())) {
                edit.putString(areaName, bean.getAreaName());
            }
            if (!TextUtils.isEmpty(bean.getCompanyName())) {
                edit.putString(companyName, bean.getCompanyName());
            }
            if (!TextUtils.isEmpty(bean.getCompanyType())) {
                edit.putString(companyType, bean.getCompanyType());
            }
            if (!TextUtils.isEmpty(bean.getContacts())) {
                edit.putString(contacts, bean.getContacts());
            }
            if (!TextUtils.isEmpty(bean.getAddress())) {
                edit.putString(address, bean.getAddress());
            }
            if (!TextUtils.isEmpty(bean.getBrandNames())) {
                edit.putString(brandNames, bean.getBrandNames());
            }
            if (!TextUtils.isEmpty(bean.getPosition())) {
                edit.putString(position, bean.getPosition());
            }
            if (!TextUtils.isEmpty(bean.getContactNumber())) {
                edit.putString(contactNumber, bean.getContactNumber());
            }
            if (!TextUtils.isEmpty(bean.getOfficePhone())) {
                edit.putString(officePhone, bean.getOfficePhone());
            }
            if (bean.getOnlineStatus() != -1) {
                edit.putInt(onlineStatus, bean.getOnlineStatus());
            }
            edit.putBoolean(isLogin, bean.isLogin());
            edit.commit();
        }
    }

    public UserInfo getUser() {
        if (!TextUtils.isEmpty(spf.getString(this.id, null))) {
            UserInfo bean = new UserInfo();
            bean.setUid(spf.getString(this.id, ""));
            bean.setUserName(spf.getString(this.userName, "暂无"));
            bean.setSk(spf.getString(this.sk, ""));
            bean.setIdCardNum(spf.getString(this.idCardNum, ""));
            bean.setMobilePhone(spf.getString(this.mobilePhone, "暂无"));
            bean.setPassword(spf.getString(this.password, ""));
            bean.setAreaName(spf.getString(this.areaName, "暂无"));
            bean.setCompanyName(spf.getString(this.companyName, "暂无"));
            bean.setCompanyType(spf.getString(this.companyType, "暂无"));
            bean.setContactNumber(spf.getString(this.contactNumber, "暂无"));
            bean.setLicense(spf.getString(this.license, "暂无"));
            bean.setPosition(spf.getString(this.position, "暂无"));
            bean.setOfficePhone(spf.getString(this.officePhone, ""));
            bean.setContacts(spf.getString(this.contacts, "暂无"));
            bean.setEmail(spf.getString(this.email, "暂无"));
            bean.setWxAccount(spf.getString(this.wxAccount, "暂无"));
            bean.setWbAccount(spf.getString(this.wbAccount, ""));
            bean.setQqAccount(spf.getString(this.qqAccount, ""));
            bean.setQqAccount(spf.getString(this.qqAccount, ""));
            bean.setAddress(spf.getString(this.address, "暂无"));
            bean.setHeadImage(spf.getString(this.headImage, "暂无"));
            bean.setBrandNames(spf.getString(this.brandNames, "暂无"));
            bean.setStatus(spf.getInt(this.status, -1));
            bean.setUserCode(spf.getString(this.userCode, ""));
            bean.setSex(spf.getString(this.sex, "1"));
            bean.setBirth(spf.getString(this.birth, ""));
            bean.setAge(spf.getString(this.age, ""));
            bean.setOnlineStatus(spf.getInt(this.onlineStatus, -1));
            bean.setLogin(spf.getBoolean(this.isLogin, false));
            return bean;
        }
        return null;
    }

    public void clear() {
        edit.clear();
        edit.commit();
    }


    public String getId() {
        return spf.getString(this.id, null);
    }

    public String getJpushId() {
        return spf.getString(this.jpushId, null);
    }

    public boolean isLogin() {
        if (!TextUtils.isEmpty(getSk()))
            return true;
        else
            return false;
    }


    public String getSk() {
        return spf.getString(this.sk, null);
    }

    public void setSk(String skStr) {
        edit.putString(sk, skStr);
        edit.commit();
    }

    public void setJpushId(String skStr) {
        edit.putString(jpushId, skStr);
        edit.commit();
    }


    public String getSkStr() {
        return sk;
    }


    public void setUserName(String uname) {
        edit.putString(userName, uname);
        edit.commit();
    }


    public void setHeadImage(String headImg) {
        edit.putString(headImage, headImg);
        edit.commit();
    }


    public void setStatus(String status) {
        edit.putString(this.status, status);
        edit.commit();
    }

    public void setSex(int sex) {
        edit.putInt(this.sex, sex);
        edit.commit();
    }

    public void setId(String id) {
        edit.putString(this.id, id);
        edit.commit();
    }

    public void setLicense(String license) {
        edit.putString(this.license, license);
        edit.commit();
    }

    public void setAreaName(String areaName) {
        edit.putString(this.areaName, areaName);
        edit.commit();
    }

    public void setCompanyName(String companyName) {
        edit.putString(this.companyName, companyName);
        edit.commit();
    }

    public void setCompanyType(String companyType) {
        edit.putString(this.companyType, companyType);
        edit.commit();
    }

    public void setContacts(String contacts) {
        edit.putString(this.contacts, contacts);
        edit.commit();
    }

    public void setAddress(String address) {
        edit.putString(this.address, address);
        edit.commit();
    }

    public void setPosition(String position) {
        edit.putString(this.position, position);
        edit.commit();
    }

    public void setBrandNames(String brandNames) {
        edit.putString(this.brandNames, brandNames);
        edit.commit();
    }

    public void setContactNumber(String contactNumber) {
        edit.putString(this.contactNumber, contactNumber);
        edit.commit();
    }

    public void setOfficePhone(String officePhone) {
        edit.putString(this.officePhone, officePhone);
        edit.commit();
    }

    public void setPassword(String password) {
        edit.putString(this.password, password);
        edit.commit();
    }

    public void setDelFlag(String delFlag) {
        edit.putString(this.delFlag, delFlag);
        edit.commit();
    }

    public void setMemo(String memo) {
        edit.putString(this.memo, memo);
        edit.commit();
    }

    public void setWxAccount(String wxAccount) {
        edit.putString(this.wxAccount, wxAccount);
        edit.commit();
    }

    public void setUserCode(String userCode) {
        edit.putString(this.userCode, userCode);
        edit.commit();
    }

    public void setDisturbStatus(String disturbStatus) {
        edit.putString(this.disturbStatus, disturbStatus);
        edit.commit();
    }

    public void setRelHeadImage(String relHeadImage) {
        edit.putString(this.relHeadImage, relHeadImage);
        edit.commit();
    }

    public void setUserType(String userType) {
        edit.putString(this.userType, userType);
        edit.commit();
    }

    public void setIdCardNum(String idCardNum) {
        edit.putString(this.idCardNum, idCardNum);
        edit.commit();
    }

    public void setBirth(String birth) {
        edit.putString(this.birth, birth);
        edit.commit();
    }

    public void setEmail(String email) {
        edit.putString(this.email, email);
        edit.commit();
    }

    public void setQqAccount(String qqAccount) {
        edit.putString(this.qqAccount, qqAccount);
        edit.commit();
    }

    public void setWbAccount(String wbAccount) {
        edit.putString(this.wbAccount, wbAccount);
        edit.commit();
    }

    public void setLockStatus(String lockStatus) {
        edit.putString(this.lockStatus, lockStatus);
        edit.commit();
    }

    public void setSex(String sex) {
        edit.putString(this.sex, sex);
        edit.commit();
    }

    public void setOnlineStatus(String onlineStatus) {
        edit.putString(this.onlineStatus, onlineStatus);
        edit.commit();
    }

    public void setLastLoginTime(String lastLoginTime) {
        edit.putString(this.lastLoginTime, lastLoginTime);
        edit.commit();
    }

    public void setRegisterTime(String registerTime) {
        edit.putString(this.registerTime, registerTime);
        edit.commit();
    }

    public void setMobilePhone(String mobilePhone) {
        edit.putString(this.mobilePhone, mobilePhone);
        edit.commit();
    }


    public void setAge(String age) {
        edit.putString(this.age, age);
        edit.commit();
    }
}
