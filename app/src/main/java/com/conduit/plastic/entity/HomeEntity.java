package com.conduit.plastic.entity;

import com.conduit.plastic.entity.database.Product;

import java.util.List;

/**
 * Created by android on 2017/4/11.
 */

public class HomeEntity {

    /**
     * company : {"license":null,"areaName":"杭州","companyName":"杭州沃德塑胶有限公司","companyType":10,"contacts":"徐有银","contactNumber":"13957172045","officePhone":"","qq":"540279256","brandNames":"沃德","address":"杭州市笕丁路426号","id":"402880ed5b467218015b467969320051"}
     * products : [{"productType":10,"productName":"管材","productImg":null,"spec":"Ф40","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4e520001"},{"productType":10,"productName":"管材","productImg":null,"spec":"Ф50","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4e710002"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ63","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4e820003"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ75","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4e980004"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ90","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4ea70005"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ110","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4eb50006"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ140","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4ec40007"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ160","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4ed30008"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ225","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4ee20009"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ280","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4ef1000a"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ315","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4efe000b"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ400","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4f0b000c"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ20","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4f18000d"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ25","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4f2e000e"},{"productType":10,"productName":"管材","productImg":null,"spec":"Φ32","standard":10,"texture":10,"describes":null,"brandName":"沃德","id":"402880ed5b569ac3015b569d4f3d000f"}]
     */

    private SellerEntity company;
    private List<Product> products;

    public SellerEntity getCompany() {
        return company;
    }

    public void setCompany(SellerEntity company) {
        this.company = company;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
