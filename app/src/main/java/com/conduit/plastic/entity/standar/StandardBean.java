package com.conduit.plastic.entity.standar;

import java.io.Serializable;

public class StandardBean implements Serializable {
    /**
     * specName1 : 1+1/2寸
     * describes :
     * standard : 20
     * specName2 : 48.26
     * id : 402880ed5b2230b2015b22394d010058
     */

    private String specName1;
    private String describes;
    private int standard;
    private String specName2;
    private String id;
    private boolean isSelected=false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSpecName1() {
        return specName1;
    }

    public void setSpecName1(String specName1) {
        this.specName1 = specName1;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getSpecName2() {
        return specName2;
    }

    public void setSpecName2(String specName2) {
        this.specName2 = specName2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}