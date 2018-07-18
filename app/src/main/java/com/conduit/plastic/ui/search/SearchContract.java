package com.conduit.plastic.ui.search;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.GoodsEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


public interface SearchContract {
    interface Model extends BaseModel {
        Observable<BaseEntity> serachList(Map<String, Object> request, int page);

        Observable<BaseEntity<List<GoodsEntity>>> serachHistory(int pageIndex);
    }

    interface View extends BaseView {
        void refreshList(BaseEntity userMessageEntity);

        void loadMoreList(BaseEntity userMessageEntity);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void serachList(Map<String,Object> request,int page);

        public abstract void serachHistory(int pageIndex);

    }
}
