package com.conduit.plastic.ui.productname;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.ProductNameAllEntity;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public class ProductNameModel implements ProductNameContract.Model {


    @Override
    public Observable<BaseEntity<ProductNameAllEntity>> productName() {
        return Networks.getInstance().getProductApi().productNameAll().compose(RxSchedulers.<BaseEntity<ProductNameAllEntity>>io_main());
    }
}
