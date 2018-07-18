package com.conduit.plastic.ui.addbrand;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/7.
 */

public class AddBrandModel implements AddBrandContract.Model {
    @Override
    public Observable<BaseEntity<BrandEntity>> addBrand(Map<String, String> map) {
        return Networks.getInstance().getProductApi().addBrand(map).compose(RxSchedulers.<BaseEntity<BrandEntity>>io_main());
    }

}