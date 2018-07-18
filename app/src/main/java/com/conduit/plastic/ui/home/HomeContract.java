package com.conduit.plastic.ui.home;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public interface HomeContract {
    interface Model extends BaseModel {

        Observable<BaseEntity<List<BannerEntity>>> bannerList(int adType);
        Observable<BaseEntity<SellerEntity>> company(String id);
        Observable<BaseEntity> callPhone(String phone, String companyId);
    }


    interface View extends BaseView {
        void bannerList(List<BannerEntity> baseEntity, int adType);
    }
    interface PersonalView extends BaseView {
        void bannerList(List<BannerEntity> baseEntity, int adType);
        void company(SellerEntity baseEntity);
        void callPhone(int type);
    }


    abstract class HomePresenter extends BasePresenter<Model, View> {
        public abstract void bannerList(int adType);
    }
    abstract class PersonalPresenter extends BasePresenter<Model, PersonalView> {
        public abstract void bannerList(int adType);
        public abstract void company(String id);
        public abstract void callPhone(String phone,String companyId);
    }
}
