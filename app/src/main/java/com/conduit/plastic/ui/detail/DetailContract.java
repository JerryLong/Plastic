package com.conduit.plastic.ui.detail;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public interface DetailContract {
    interface Model extends BaseModel {
        Observable<BaseEntity> handleProduct(Map<String, Object> queryMap, int page);
    }


    interface View extends BaseView {
//        void refreshList(List baseEntity);
//
//        void loadMoreList(List<SellerEntity> baseEntity);
    }


    abstract class DealPresenter extends BasePresenter<Model, View> {
        public abstract void handleProduct(Map<String, Object> queryMap, int page);
    }
}
