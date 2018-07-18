package com.conduit.plastic.ui.detail;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public class DetailModel implements DetailContract.Model {


    @Override
    public Observable<BaseEntity> handleProduct(Map<String, Object> queryMap, int page) {
        return Networks.getInstance().getProductApi().handleProduct(queryMap,page, Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity>io_main());
    }
}
