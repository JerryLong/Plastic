package com.conduit.plastic.ui.splash;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/6.
 */

public interface SplashContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<List<BannerEntity>>> bannerList(int adType);
    }

    interface View extends BaseView {
        void bannerList(List<BannerEntity> baseEntity);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void bannerList(int adType);
    }
}
