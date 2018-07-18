package com.conduit.plastic.ui.addbrand;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/7.
 */

public interface AddBrandContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<BrandEntity>> addBrand(Map<String, String> map);
    }

    interface View extends BaseView {
        void addBrand(BaseEntity<BrandEntity> baseEntity);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addBrand(Map<String, String> map);
    }
}
