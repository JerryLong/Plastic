package com.conduit.plastic.entity;

import java.io.Serializable;

/**
 * Created by Jim Long on 2017/12/12.
 */

public class RecommendEntity implements Serializable {

    /**
     * productType : 30
     * brandName : 牧桥
     * describes :
     * priceStar : 3
     * qualityStar : 3
     * baseCompanyId : 2c9070985cb6b814015cf7a2a5780015
     * baseCompanyName : 斯德宝泵阀（苏州）有限公司
     * fullName : 测试阀门
     * id : 2c9070986048bcba016048bf6e4b0001
     */

    private int productType;
    private String brandName;
    private String describes;
    private int priceStar;
    private int qualityStar;
    private String baseCompanyId;
    private String baseCompanyName;
    private String fullName;
    private String id;

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

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public int getPriceStar() {
        return priceStar;
    }

    public void setPriceStar(int priceStar) {
        this.priceStar = priceStar;
    }

    public int getQualityStar() {
        return qualityStar;
    }

    public void setQualityStar(int qualityStar) {
        this.qualityStar = qualityStar;
    }

    public String getBaseCompanyId() {
        return baseCompanyId;
    }

    public void setBaseCompanyId(String baseCompanyId) {
        this.baseCompanyId = baseCompanyId;
    }

    public String getBaseCompanyName() {
        return baseCompanyName;
    }

    public void setBaseCompanyName(String baseCompanyName) {
        this.baseCompanyName = baseCompanyName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
