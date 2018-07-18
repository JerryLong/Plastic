package com.conduit.plastic.ui.main.activity;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.RecommendEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.ui.release.contract.DemandContract;

import java.util.List;

import io.reactivex.Observable;


public interface MainContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<UserInfo>> info();

        Observable<BaseEntity<List<DemandEntity>>> demandList(RequestParams requestParams);

        Observable<BaseEntity<List<BannerEntity>>> bannerList(int adType);

        Observable<BaseEntity<List<RecommendEntity>>> recommendList(RequestParams params);

    }


    interface View extends BaseView {
        void info(BaseEntity<UserInfo> baseEntity);

        void bannerList(List<BannerEntity> baseEntity);

    }

    interface DemandView extends BaseView {

        void demandList(List<DemandEntity> baseEntity);

    }

    interface RecommendView extends BaseView {

        void recommendList(List<RecommendEntity> baseEntity);

    }


    abstract class MainPresenter extends BasePresenter<Model, View> {
        public abstract void info();

        public abstract void bannerList(int adType);

    }

    abstract class DemandPresenter extends BasePresenter<Model, DemandView> {
        public abstract void demandList(RequestParams requestParams);
    }

    abstract class RecommendPresenter extends BasePresenter<Model, RecommendView> {
        public abstract void recommendList(RequestParams requestParams);
    }
}
