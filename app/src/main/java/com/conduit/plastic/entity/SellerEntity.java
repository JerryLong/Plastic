package com.conduit.plastic.entity;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by android on 2017/4/11.
 */

public class SellerEntity implements Serializable {

    /**
     * license : null
     * areaName : 杭州
     * companyName : 杭州沃德塑胶有限公司
     * companyType : 10
     * contacts : 徐有银
     * contactNumber : 13957172045
     * officePhone :
     * qq : 540279256
     * brandNames : 沃德
     * address : 杭州市笕丁路426号
     * id : 402880ed5b467218015b467969320051
     * productImg: null,
     productName: "工业管",
     productType: 10,
     brandName: "雁荡",
     spec: "Φ16",
     standard: 10,
     texture: 10,
     describes: null,
     totalQuantity: 2,
     stockStatus: 40,
     id: "2c9070985c3e0ab0015c5d45ecff0002"
     */
    /**
     * productImg : null
     * productName : 工业管
     * productType : 10
     * brandName : 雁荡
     * spec : Φ16
     * standard : 10
     * texture : 10
     * describes : null
     * totalQuantity : 2
     * stockStatus : 40
     *
     */
    private Object license;
    private String areaName;
    private String companyName;
    private int companyType;
    private String contacts;
    private String contactNumber;
    private String officePhone;
    private String qq;
    private String brandNames;
    private String address;
    private String id;
    private Object productImg;
    private String productName;
    private int productType;
    private String brandName;
    private String spec;
    private int standard;
    private int texture;
    private Object describes;
    private int totalQuantity;
    private int stockStatus=0;




    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    public String getAreaName() {
        return TextUtils.isEmpty(areaName) ? "暂无" : areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCompanyName() {
        return TextUtils.isEmpty(companyName) ? "暂无" : companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyType() {
        return companyType;
    }

    public void setCompanyType(int companyType) {
        this.companyType = companyType;
    }

    public String getContacts() {
        return TextUtils.isEmpty(contacts) ? "暂无" : contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactNumber() {
        return TextUtils.isEmpty(contactNumber) ? "暂无" : contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOfficePhone() {

        return TextUtils.isEmpty(officePhone) ? "暂无" : officePhone;
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

    public String getBrandNames() {
        return TextUtils.isEmpty(brandNames) ? "暂无" : brandNames;
    }

    public void setBrandNames(String brandNames) {
        this.brandNames = brandNames;
    }

    public String getAddress() {
        return TextUtils.isEmpty(address) ? "暂无" : address;
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

    public Object getProductImg() {
        return productImg;
    }

    public void setProductImg(Object productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public Object getDescribes() {
        return describes;
    }

    public void setDescribes(Object describes) {
        this.describes = describes;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(int stockStatus) {
        this.stockStatus = stockStatus;
    }
}
