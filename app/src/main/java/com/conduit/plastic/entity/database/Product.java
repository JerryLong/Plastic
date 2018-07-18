package com.conduit.plastic.entity.database;

import android.text.TextUtils;

import com.conduit.plastic.R;
import com.conduit.plastic.common.PlasticApp;

import org.litepal.crud.DataSupport;

/**
 * Created by android on 2017/3/21.
 */

public class Product extends DataSupport {

    /**
     * productType : 10
     * productName : 管材
     * productImg : null
     * spec : Ф40
     * standard : 10
     * texture : 10
     * describes : null
     * brandName : 沃德
     * id : 402880ed5b569ac3015b569d4e520001
     */

    public int productType;
    public String productName;
    public String productImg;
    public String spec;
    public int standard;
    public int texture;
    public String describes;
    public String brandName;
    public String pid;
    String[] strings = new String[]{"国标(Φ)", "国标(DIN)", "美标/英标", "日标"};

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getProductName() {
        return TextUtils.isEmpty(productName) ? "暂无" : productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getSpec() {
        return TextUtils.isEmpty(spec) ? "暂无" : spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStandard() {
        return strings[(standard / 10) - 1];
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getTexture() {
//        (position + 1) * 10)
        return PlasticApp.getInstance().getResources().getStringArray(R.array.texture_array)[(texture / 10) - 1];
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public String getDescribes() {
        return TextUtils.isEmpty(describes) ? "暂无" : describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getBrandName() {
        return TextUtils.isEmpty(brandName) ? "暂无" : brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
