package com.conduit.plastic.ui.seller;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public interface SellerContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<List<SellerEntity>>> companyList(Map<String, Object> queryMap, int page);
        Observable<BaseEntity<List<SellerEntity>>> handleProduct(Map<String, Object> queryMap, int page);
    }


    interface View extends BaseView {
        void refreshList(List<SellerEntity> baseEntity);

        void loadMoreList(List<SellerEntity> baseEntity);
    }


    abstract class SellerPresenter extends BasePresenter<Model, View> {
        public abstract void companyList(Map<String, Object> queryMap, int page);
        public abstract void handleProduct(Map<String, Object> queryMap, int page);
    }
}
