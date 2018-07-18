package com.conduit.plastic.ui.deallist;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandAllEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.List;

import io.reactivex.Observable;


public interface DealListContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<BrandAllEntity>> brandList(int pageIndex);
    }

    interface View extends BaseView {
        void refreshList(List<SellerEntity> baseEntity);

        void loadMoreList(List<SellerEntity> baseEntity);

    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void brandList(int pageIndex);

    }
}
