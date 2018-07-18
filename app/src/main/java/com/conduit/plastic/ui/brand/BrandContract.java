package com.conduit.plastic.ui.brand;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandAllEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import io.reactivex.Observable;


public interface BrandContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<BrandAllEntity>> brandList(int pageIndex);
    }

    interface View extends BaseView {
        void brandList(BrandAllEntity brandAllEntity);

    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void brandList(int pageIndex);

    }
}
