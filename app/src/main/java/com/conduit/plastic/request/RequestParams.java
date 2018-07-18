package com.conduit.plastic.request;

import com.conduit.plastic.global.Constants;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim Long on 2017/12/1.
 */

public class RequestParams implements Serializable{
    public int page;
    public int pageSize= Constants.PER_PAGE;
    public String productType;
    public String region;
    public String content;
    public String title;
    public String productNameId;
    public String brandId;
    public String standard;
    public String specId;
    public String texture;
    public String quantity;
    public String unit;
    public String validityDate;
    public String describes;
    public String productImg;
    public String isConceal;
    public List<LocalMedia> mList = new ArrayList<>();
}
