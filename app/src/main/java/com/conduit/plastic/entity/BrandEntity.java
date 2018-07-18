package com.conduit.plastic.entity;

import java.io.Serializable;

/**
 * Created by android on 2017/3/8.
 */

public class BrandEntity implements Serializable {
    /**
     * companyName : 上海耀玮塑胶有限公司
     * contacts : null
     * contactNumber : null
     * describes :
     * brandLogo : http://ggbucket-1253515764.cosgz.myqcloud.com/image/sys/201704/27164416_1889820573420851410.jpg
     * brandNameCn : 耀玮
     * brandNameEn :
     * brandPlace : 上海
     * shortening :
     * address : null
     * id : 402880eb5bae92d8015bae951aea0001
     * region : 10
     */

    private String companyName;
    private String contacts;
    private String contactNumber;
    private String describes;
    private String brandLogo;
    private String brandNameCn;
    private String brandNameEn;
    private String brandPlace;
    private String shortening;
    private String address;
    private String id;
    private int region;

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

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandNameCn() {
        return brandNameCn;
    }

    public void setBrandNameCn(String brandNameCn) {
        this.brandNameCn = brandNameCn;
    }

    public String getBrandNameEn() {
        return brandNameEn;
    }

    public void setBrandNameEn(String brandNameEn) {
        this.brandNameEn = brandNameEn;
    }

    public String getBrandPlace() {
        return brandPlace;
    }

    public void setBrandPlace(String brandPlace) {
        this.brandPlace = brandPlace;
    }

    public String getShortening() {
        return shortening;
    }

    public void setShortening(String shortening) {
        this.shortening = shortening;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }
}
