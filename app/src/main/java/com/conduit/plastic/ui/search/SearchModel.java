package com.conduit.plastic.ui.search;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.GoodsEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;
import com.orhanobut.logger.Logger;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


public class SearchModel implements SearchContract.Model {


    @Override
    public Observable<BaseEntity> serachList(Map<String, Object> request, int page) {
        return Networks.getInstance().getProductApi().companyList(request,page, Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity>io_main());
    }

    @Override
    public Observable<BaseEntity<List<GoodsEntity>>> serachHistory(int pageIndex) {
        BaseEntity<List<GoodsEntity>> data;
        if (pageIndex == 1) {
            data = new BaseEntity();
            List<GoodsEntity> list = DataSupport.findAll(GoodsEntity.class);
            data.setData(list);
        } else {
            data = new BaseEntity();
            List<GoodsEntity> list = new ArrayList<>();
            if (pageIndex * 20 <= DataSupport.count(GoodsEntity.class))
                list = DataSupport.findAll(GoodsEntity.class).subList(pageIndex * 20, (pageIndex + 1) * 20);
            data.setData(list);
        }
        Logger.i("==model==" + data.getData().size());
        return Observable.just(data);
    }


    //    /**
//     * 根据用户 Id 获取用户信息
//     * @param userId
//     * @return
//     */
//    @Override
//    public Observable<UserInfoEntity> getUserInfoById(int userId) {
//        return Networks.getInstance().getUserApi().getUserInfoById(userId)
//                .compose(RxSchedulers.<UserInfoEntity>io_main());
//    }
}
