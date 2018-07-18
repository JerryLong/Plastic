package com.conduit.plastic.ui.seller;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.entity.database.Product;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public class SellerModel implements SellerContract.Model {

    @Override
    public Observable<BaseEntity<List<SellerEntity>>> companyList(Map<String, Object> queryMap, int page) {
        return Networks.getInstance().getProductApi().companyList(queryMap,page, Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity<List<SellerEntity>>>io_main());
    }

    @Override
    public Observable<BaseEntity<List<SellerEntity>>> handleProduct(Map<String, Object> queryMap, int page) {
        return Networks.getInstance().getProductApi().handleProduct(queryMap,page, Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity<List<SellerEntity>>>io_main());
    }
}
