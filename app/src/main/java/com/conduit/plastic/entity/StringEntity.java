package com.conduit.plastic.entity;

/**
 * Created by Jim Long on 2017/12/25.
 */

public class StringEntity {
    private int index;
    private String title;
    private boolean isSelected;

    public StringEntity(int index, String title, boolean isSelected) {
        this.index = index;
        this.title = title;
        this.isSelected = isSelected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
