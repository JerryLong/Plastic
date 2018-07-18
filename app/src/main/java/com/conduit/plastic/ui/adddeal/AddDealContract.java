package com.conduit.plastic.ui.adddeal;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public interface AddDealContract {
    interface Model extends BaseModel {
        Observable<BaseEntity> disposeProduct(Map<String, Object> queryMap);
    }


    interface View extends BaseView {
        void finishAdd();
    }


    abstract class AddDealPresenter extends BasePresenter<Model, View> {
        public abstract void disposeProduct(Map<String, Object> queryMap);
    }
}
