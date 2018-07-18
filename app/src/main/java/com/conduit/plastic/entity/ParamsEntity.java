package com.conduit.plastic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jim Long on 2017/12/28.
 */

public class ParamsEntity implements Serializable {

    private List<ProportionsBean> proportions;
    private List<ProportionsBean> diameters;
    private List<ProportionsBean> thickneses;
    private List<ProportionsBean> widths;

    public List<ProportionsBean> getProportions() {
        return proportions;
    }

    public void setProportions(List<ProportionsBean> proportions) {
        this.proportions = proportions;
    }

    public List<ProportionsBean> getDiameters() {
        return diameters;
    }

    public void setDiameters(List<ProportionsBean> diameters) {
        this.diameters = diameters;
    }

    public List<ProportionsBean> getThickneses() {
        return thickneses;
    }

    public void setThickneses(List<ProportionsBean> thickneses) {
        this.thickneses = thickneses;
    }

    public List<ProportionsBean> getWidths() {
        return widths;
    }

    public void setWidths(List<ProportionsBean> widths) {
        this.widths = widths;
    }

    public static class ProportionsBean implements Serializable{
        /**
         * paraType : 2
         * width : null
         * thickness : null
         * diameter : null
         * proportion : 0.91
         * propertyType : 4
         * id : 40281981601b810101601b8361790023
         */

        private int paraType;
        private float width;
        private int thickness;
        private int diameter;
        private float proportion;
        private int propertyType;
        private String id;
        private boolean isSelected=false;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getParaType() {
            return paraType;
        }

        public void setParaType(int paraType) {
            this.paraType = paraType;
        }

        public float getWidth() {
            return width;
        }

        public void setWidth(float width) {
            this.width = width;
        }

        public int getThickness() {
            return thickness;
        }

        public void setThickness(int thickness) {
            this.thickness = thickness;
        }

        public int getDiameter() {
            return diameter;
        }

        public void setDiameter(int diameter) {
            this.diameter = diameter;
        }

        public float getProportion() {
            return proportion;
        }

        public void setProportion(float proportion) {
            this.proportion = proportion;
        }

        public int getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(int propertyType) {
            this.propertyType = propertyType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
