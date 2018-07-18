package com.conduit.plastic.ui.adddeal;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public class AddDealModel implements AddDealContract.Model {


    @Override
    public Observable<BaseEntity> disposeProduct(Map<String, Object> queryMap) {
        return Networks.getInstance().getProductApi().disposeProduct(queryMap).compose(RxSchedulers.<BaseEntity>io_main());
    }
}
