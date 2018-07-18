package com.conduit.plastic.ui.info.fragment;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;
import com.conduit.plastic.request.ModifyRequest;

import io.reactivex.Observable;


public interface PersonContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<UserInfo>> modify(ModifyRequest request);
        Observable<BaseEntity<UserInfo>> modifyCompany(ModifyRequest request);
    }


    interface View extends BaseView {
        void modify(BaseEntity<UserInfo> baseEntity);
    }

    abstract class PersonPresenter extends BasePresenter<Model, View> {

        public abstract void modify(ModifyRequest request);
        public abstract void modifyCompany(ModifyRequest request);


    }

}
