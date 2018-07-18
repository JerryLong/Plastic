package com.conduit.plastic.entity;

import com.conduit.plastic.entity.standar.StandardBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/3/31.
 */

public class SpecEntity implements Serializable {

    private List<StandardBean> japanStandard = new ArrayList<>();
    private List<StandardBean> chinaStandard1 = new ArrayList<>();
    private List<StandardBean> japanStandard1 = new ArrayList<>();
    private List<StandardBean> americaStandard1 = new ArrayList<>();
    private List<StandardBean> englandStandard1 = new ArrayList<>();
    private List<StandardBean> americaStandard = new ArrayList<>();
    private List<StandardBean> englandStandard = new ArrayList<>();
    private List<StandardBean> chinaStandard = new ArrayList<>();

    public List<StandardBean> getJapanStandard() {
        return japanStandard;
    }

    public void setJapanStandard(List<StandardBean> japanStandard) {
        this.japanStandard = japanStandard;
    }

    public List<StandardBean> getAmericaStandard() {
        return americaStandard;
    }

    public void setAmericaStandard(List<StandardBean> americaStandard) {
        this.americaStandard = americaStandard;
    }

    public List<StandardBean> getEnglandStandard() {
        return englandStandard;
    }

    public void setEnglandStandard(List<StandardBean> englandStandard) {
        this.englandStandard = englandStandard;
    }

    public List<StandardBean> getChinaStandard() {
        return chinaStandard;
    }

    public void setChinaStandard(List<StandardBean> chinaStandard) {
        this.chinaStandard = chinaStandard;
    }

    public List<StandardBean> getChinaStandard1() {
        return chinaStandard1;
    }

    public void setChinaStandard1(List<StandardBean> chinaStandard1) {
        this.chinaStandard1 = chinaStandard1;
    }

    public List<StandardBean> getJapanStandard1() {
        return japanStandard1;
    }

    public void setJapanStandard1(List<StandardBean> japanStandard1) {
        this.japanStandard1 = japanStandard1;
    }

    public List<StandardBean> getAmericaStandard1() {
        return americaStandard1;
    }

    public void setAmericaStandard1(List<StandardBean> americaStandard1) {
        this.americaStandard1 = americaStandard1;
    }

    public List<StandardBean> getEnglandStandard1() {
        return englandStandard1;
    }

    public void setEnglandStandard1(List<StandardBean> englandStandard1) {
        this.englandStandard1 = englandStandard1;
    }
}
