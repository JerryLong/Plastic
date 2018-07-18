package com.conduit.plastic.ui.info.phone;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;
import com.conduit.plastic.request.UserRequest;

import io.reactivex.Observable;


public interface PhoneContract {
    interface Model extends BaseModel {
        Observable<BaseEntity> valicode(UserRequest request);
        Observable<BaseEntity> bindingPhone(UserRequest request);
    }


    interface View extends BaseView {
        void bindingPhone(BaseEntity baseEntity);
        void valicode(BaseEntity baseEntity);
    }

    abstract class PhonePresenter extends BasePresenter<Model, View> {

        public abstract void valicode(UserRequest request);
        public abstract void bindingPhone(UserRequest request);


    }

}
