package com.conduit.plastic.ui.area;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public class AreaModel implements AreaContract.Model {

    @Override
    public Observable<BaseEntity<List<AreaEntity>>> areaList() {
        return Networks.getInstance().getOtherApi().areaList().compose(RxSchedulers.<BaseEntity<List<AreaEntity>>>io_main());
    }
}
