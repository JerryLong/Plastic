package com.conduit.plastic.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/4/28.
 */

public class BrandAllEntity {

    private ArrayList<BrandEntity> northChinaBrands;
    private ArrayList<BrandEntity> eastChinaBrands;
    private ArrayList<BrandEntity> southChinaBrands;
    private ArrayList<BrandEntity> recomChinaBrands;
    private ArrayList<BrandEntity> abroadBrands;
    private ArrayList<BrandEntity> taiwanBrands;
    private ArrayList<BrandEntity> glueBrands;

    public ArrayList<BrandEntity> getNorthChinaBrands() {
        return northChinaBrands;
    }

    public void setNorthChinaBrands(ArrayList<BrandEntity> northChinaBrands) {
        this.northChinaBrands = northChinaBrands;
    }

    public ArrayList<BrandEntity> getEastChinaBrands() {
        return eastChinaBrands;
    }

    public void setEastChinaBrands(ArrayList<BrandEntity> eastChinaBrands) {
        this.eastChinaBrands = eastChinaBrands;
    }

    public ArrayList<BrandEntity> getSouthChinaBrands() {
        return southChinaBrands;
    }

    public void setSouthChinaBrands(ArrayList<BrandEntity> southChinaBrands) {
        this.southChinaBrands = southChinaBrands;
    }

    public ArrayList<BrandEntity> getAbroadBrands() {
        return abroadBrands;
    }

    public void setAbroadBrands(ArrayList<BrandEntity> abroadBrands) {
        this.abroadBrands = abroadBrands;
    }

    public ArrayList<BrandEntity> getTaiwanBrands() {
        return taiwanBrands;
    }

    public void setTaiwanBrands(ArrayList<BrandEntity> taiwanBrands) {
        this.taiwanBrands = taiwanBrands;
    }

    public ArrayList<BrandEntity> getRecomChinaBrands() {
        return recomChinaBrands;
    }

    public void setRecomChinaBrands(ArrayList<BrandEntity> recomChinaBrands) {
        this.recomChinaBrands = recomChinaBrands;
    }

    public ArrayList<BrandEntity> getGlueBrands() {
        return glueBrands;
    }

    public void setGlueBrands(ArrayList<BrandEntity> glueBrands) {
        this.glueBrands = glueBrands;
    }
}
