package com.conduit.plastic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2017/4/13.
 */

public class ProductNameEntity implements Serializable {
    /**
     * productNames : []
     * productName : 承插式截止阀
     * describes :
     * imgPath : null
     * pmType : 30
     * id : 402880ed5b7b311c015b7b389a35014d
     */

    private String productName;
    private String describes;
    private Object imgPath;
    private int pmType;
    private String id;
    private List<ProductNameEntity> productNames = new ArrayList<>();

    public ProductNameEntity(String productName, String id, List<ProductNameEntity> productNames) {
        this.productName = productName;
        this.id = id;
        this.productNames = productNames;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public Object getImgPath() {
        return imgPath;
    }

    public void setImgPath(Object imgPath) {
        this.imgPath = imgPath;
    }

    public int getPmType() {
        return pmType;
    }

    public void setPmType(int pmType) {
        this.pmType = pmType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductNameEntity> getProductNames() {
        if (productNames == null)
            productNames = new ArrayList<ProductNameEntity>();
        return productNames;
    }

    public void setProductNames(List<ProductNameEntity> productNames) {
        this.productNames = productNames;
    }
/**
 * productName : 由令式底阀
 * describes :
 * imgPath : null
 * pmType : 30
 * productNames : []
 * id : 402880ed5b7b311c015b7b3899e30147
 */

}
