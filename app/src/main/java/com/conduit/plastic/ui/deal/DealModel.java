package com.conduit.plastic.ui.deal;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public class DealModel implements DealContract.Model {


    @Override
    public Observable<BaseEntity<List<SellerEntity>>> handleProduct(Map<String, Object> queryMap, int page) {
        return Networks.getInstance().getProductApi().handleProduct(queryMap,page, Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity<List<SellerEntity>>>io_main());
    }
}
