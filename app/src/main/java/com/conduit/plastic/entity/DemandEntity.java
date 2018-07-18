package com.conduit.plastic.entity;

import android.text.TextUtils;

import com.conduit.plastic.R;
import com.conduit.plastic.common.PlasticApp;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.user.UserUtils;
import com.luck.picture.lib.config.PictureMimeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Jim Long on 2017/11/30.
 */

public class DemandEntity implements Serializable {

    /**
     * userId : 402880ed5b0dbe04015b0dc2b2020000
     * "mobilePhone": "15281700093",
     * productImg : null
     * productName : null
     * productType : null
     * brandName :
     * spec : Φ355
     * standard : 30
     * texture : 130
     * describes : 哈哈哈哈啊啊所大所多哈哈
     * title : 哈哈哈
     * createDate : 2017-12-07 15:57:22
     * quantity : 50
     * unit : 1
     * validityDate : 2018-01-02 00:00:00
     * isConceal : 1
     * brandId : null
     * companyName : null
     * productImg : null
     * status : 0
     * fullName : null
     * id : 2c907098602ff99301602ffa9dab0000
     */

    public int page;
    public int pageSize = Constants.PER_PAGE;
    public String mobilePhone;
    public String content;
    public String userId;
    public String userName;
    public String specId;
    public List<LocalMedia> mList = new ArrayList<>();
    public String productNameId;
    public String productImgs;
    public String[] productImg;
    public String productName = "";
    public String productType = "";
    public String brandName = "";
    public String spec = "";
    public String standard = "";
    public String texture = "";
    public String describes = "";
    public String title = "";
    public String createDate = "";
    public String quantity = "0";
    public boolean isImage = false;
    public String unit = "1";
    public String validityDate = "";
    public String companyName = "";
    public int isConceal;//0匿名 1不匿名
    public String brandId = "";
    public String status = "";
    public String fullName = "";
    public String id = "";
    public boolean isHideUser = true;

    public DemandEntity() {
    }

    public String getUserName() {

        return !TextUtils.isEmpty(userName) && isConceal == 1 ? userName : UserUtils.getInstance().getUser().getContacts();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhone() {
        return TextUtils.isEmpty(mobilePhone) ? "" : mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(String productImgs) {
        this.productImgs = productImgs;
    }

    public String[] getProductImg() {
        return productImg;
    }

    public void setProductImg(String[] productImg) {
        this.productImg = productImg;
    }

    public String getCompanyName() {
        if (TextUtils.isEmpty(companyName))
            companyName = "暂无";
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        if (TextUtils.isEmpty(productName))
            productName = "暂无";
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBrandName() {
        if (TextUtils.isEmpty(brandName))
            brandName = "暂无";
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSpec() {
        if (TextUtils.isEmpty(spec))
            spec = "暂无";
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public List<LocalMedia> getImageList() {
        if (getProductImg() == null && getProductImg().length == 0) {
            mList = new ArrayList<>();
        } else {
            mList = new ArrayList<>();
            for (String s : Arrays.asList(getProductImg())) {
                LocalMedia local = new LocalMedia();
                local.setPath(s);
                local.setMimeType(PictureMimeType.ofImage());
                local.setPictureType("image/jpeg");
                mList.add(local);
            }
        }
        return mList;
    }

    public List<LocalMedia> getmList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
//        else {
//            mList = new ArrayList<>();
//            for (String s : Arrays.asList(getProductImg())) {
//                LocalMedia local = new LocalMedia();
//                local.setPath(s);
//                local.setMimeType(PictureMimeType.ofImage());
//                local.setPictureType("image/jpeg");
//                mList.add(local);
//            }
//        }
        return mList;
    }

    public void setmList(List<LocalMedia> mList) {

        this.mList = mList;
    }

    public String getProductNameId() {
        return productNameId;
    }

    public void setProductNameId(String productNameId) {
        this.productNameId = productNameId;
    }

    public String getDescribes() {
        return TextUtils.isEmpty(describes) ? " " : describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStandard() {
        return standard;
    }

    public String getStandards() {
        String standards = standard;
        if (isNumeric(standards) && (Integer.parseInt(standards) / 10 - 1) >= 0) {
            standards = PlasticApp.getInstance().getResources().getStringArray(R.array.standard_array)[Integer.parseInt(standards) / 10 - 1];
        } else {
            standards = "暂无";
        }
        return standards;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getTexture() {
        return texture;
    }

    public String getTextures() {
        String textures = texture;
        if (isNumeric(textures) && (Integer.parseInt(textures) / 10 - 1) >= 0) {
            textures = PlasticApp.getInstance().getResources().getStringArray(R.array.texture_array)[Integer.parseInt(textures) / 10 - 1];
        } else {
            textures = "暂无";
        }
        return textures;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getQuantity() {
        if (isNumeric(quantity)) {
            quantity = "未知";
        }
        return quantity;
    }

    public String getQuantitys() {
        if (!isNumeric(quantity)) {
            quantity = "0";
        }
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValidityDate() {
        return validityDate.substring(0, 10);
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public int getIsConceal() {
        return isConceal;
    }

    public void setIsConceal(int isConceal) {
        this.isConceal = isConceal;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        if (TextUtils.isEmpty(fullName))
            fullName = "暂无";
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

    public static boolean isNumeric(String str) {
        if (!TextUtils.isEmpty(str)) {
            Pattern pattern = Pattern.compile("[0-9]*");
            return pattern.matcher(str).matches();
        } else {
            return false;
        }

    }
}
