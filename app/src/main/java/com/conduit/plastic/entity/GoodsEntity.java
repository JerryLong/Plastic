package com.conduit.plastic.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by android on 2017/3/20.
 */

public class GoodsEntity extends DataSupport {
    public int id;
    public String brand;
    public String name;
    public String spec;
    public String standard;
    public String quality;
    public String images;

    public GoodsEntity(int id,String brand, String name, String spec, String standard, String quality, String images) {
        this.brand = brand;
        this.id = id;
        this.name = name;
        this.spec = spec;
        this.standard = standard;
        this.quality = quality;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
