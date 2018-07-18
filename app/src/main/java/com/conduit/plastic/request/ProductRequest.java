package com.conduit.plastic.request;

import java.io.Serializable;

/**
 * Created by android on 2017/4/7.
 */

public class ProductRequest implements Serializable {
    private String brandName;
    private String productName;
    private String standard;
    private String specId;
    private String texture;
    private String areaName;
    private String companyName;//公司名称
    private String brandNameCn;//中文品牌
    private String brandLogo;//品牌logo
    private String describe;//描述
    private int page;
    private String pageSize;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getPage() {
        return page == 0 ? 1 : page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize = "20";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBrandNameCn() {
        return brandNameCn;
    }

    public void setBrandNameCn(String brandNameCn) {
        this.brandNameCn = brandNameCn;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
