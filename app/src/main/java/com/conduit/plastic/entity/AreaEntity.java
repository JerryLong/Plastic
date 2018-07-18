package com.conduit.plastic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by android on 2017/4/12.
 */

public class AreaEntity implements Serializable {

    /**
     * areaCode : 50
     * areaName : 四川
     * areaLevel : 10
     * areaPinyin : sichuan
     * areas : []
     * id : 402880ed5ae9ae97015ae9b4b8660002
     */

    private String areaCode;
    private String areaName;
    private int areaLevel;
    private String areaPinyin;
    private String id;
    private List<AreaEntity> areas;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(int areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAreaPinyin() {
        return areaPinyin;
    }

    public void setAreaPinyin(String areaPinyin) {
        this.areaPinyin = areaPinyin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AreaEntity> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaEntity> areas) {
        this.areas = areas;
    }
}
