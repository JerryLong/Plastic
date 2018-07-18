package com.conduit.plastic.ui.mine.contract;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.request.UserRequest;

import java.util.List;

import io.reactivex.Observable;


public interface MineContract {
    interface Model extends BaseModel {
        Observable<BaseEntity> alert(UserRequest request);

        Observable<BaseEntity<List<DemandEntity>>> demandList(int page);

        Observable<BaseEntity<UserInfo>> updateUserInfo(ModifyRequest request);

        Observable<BaseEntity<UserInfo>> modifyCompany(ModifyRequest request);

        Observable<BaseEntity> feedback(RequestParams request);

        Observable<BaseEntity> logout();
    }


    interface MineView extends BaseView {

        void ordinary(BaseEntity baseEntity);


        void modify(BaseEntity<UserInfo> baseEntity);
        void demandList(List<DemandEntity> baseEntity);

    }

    abstract class MinePresenter extends BasePresenter<Model, MineView> {
        public abstract void logout();

        public abstract void modify(ModifyRequest request);

        public abstract void feedback(RequestParams request);

        public abstract void company(ModifyRequest request);

        public abstract void demandList(int page);

        public abstract void alert(UserRequest request);
    }


}
