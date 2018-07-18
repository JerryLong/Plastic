package com.conduit.plastic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jim Long on 2017/12/18.
 */

public class LocalMedia implements Serializable {
    public String path;
    public String compressPath;
    public String cutPath;
    public long duration;
    public boolean isChecked;
    public boolean isCut;
    public int position;
    public int num;
    public int mimeType;
    public String pictureType;
    public boolean compressed;
    public int width;
    public int height;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    public String getCutPath() {
        return cutPath;
    }

    public void setCutPath(String cutPath) {
        this.cutPath = cutPath;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isCut() {
        return isCut;
    }

    public void setCut(boolean cut) {
        isCut = cut;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getMimeType() {
        return mimeType;
    }

    public void setMimeType(int mimeType) {
        this.mimeType = mimeType;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public boolean isCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static List<LocalMedia> setImage(List<com.luck.picture.lib.entity.LocalMedia> mediaList) {
        List<LocalMedia> medias = new ArrayList<>();
        for (com.luck.picture.lib.entity.LocalMedia media : mediaList) {
            LocalMedia localMedia = new LocalMedia();
            localMedia.setCompressed(media.isCompressed());
            localMedia.setChecked(media.isChecked());
            localMedia.setCompressPath(media.getCompressPath());
            localMedia.setPath(media.getPath());
            localMedia.setCutPath(media.getCutPath());
            localMedia.setDuration(media.getDuration());
            localMedia.setPosition(media.getPosition());
            localMedia.setNum(media.getNum());
            localMedia.setMimeType(media.getMimeType());
            localMedia.setPictureType(media.getPictureType());
            localMedia.setWidth(media.getWidth());
            localMedia.setHeight(media.getHeight());
            medias.add(localMedia);
        }
        return medias;
    }
    public static List<com.luck.picture.lib.entity.LocalMedia> getParcelImage(List<LocalMedia> mediaList) {
        List<com.luck.picture.lib.entity.LocalMedia> medias = new ArrayList<>();
        for (LocalMedia media : mediaList) {
            com.luck.picture.lib.entity.LocalMedia localMedia = new com.luck.picture.lib.entity.LocalMedia();
            localMedia.setCompressed(media.isCompressed());
            localMedia.setChecked(media.isChecked());
            localMedia.setCompressPath(media.getCompressPath());
            localMedia.setPath(media.getPath());
            localMedia.setCutPath(media.getCutPath());
            localMedia.setDuration(media.getDuration());
            localMedia.setPosition(media.getPosition());
            localMedia.setNum(media.getNum());
            localMedia.setMimeType(media.getMimeType());
            localMedia.setPictureType(media.getPictureType());
            localMedia.setWidth(media.getWidth());
            localMedia.setHeight(media.getHeight());
            medias.add(localMedia);
        }
        return medias;
    }
}
