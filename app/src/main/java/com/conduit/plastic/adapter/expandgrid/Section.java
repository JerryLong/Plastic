package com.conduit.plastic.adapter.expandgrid;

public class Section {

    private final String name;

    public boolean isExpanded=false;

    public Section(String name,boolean isExpanded) {
        this.name = name;
        this.isExpanded = isExpanded;
    }
    public Section(String name) {
        this.name = name;
        this.isExpanded = false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public String getName() {
        return name;
    }
}
