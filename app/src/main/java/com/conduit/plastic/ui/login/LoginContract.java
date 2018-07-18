package com.conduit.plastic.ui.login;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;
import com.conduit.plastic.request.UserRequest;

import io.reactivex.Observable;


public interface LoginContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<UserEntity>> login(UserRequest request);

        Observable<BaseEntity<UserEntity>> register(UserRequest request);
        Observable<BaseEntity<UserEntity>> forget(UserRequest request);

        Observable<BaseEntity> valicode(UserRequest request);
    }

    interface RegisterView extends BaseView {
        void register(BaseEntity<UserEntity> baseEntity);

        void valicode(BaseEntity baseEntity);

    }
    interface ForgetView extends BaseView {
        void forget(BaseEntity<UserEntity> baseEntity);

        void valicode(BaseEntity baseEntity);

    }

    interface LoginView extends BaseView {
        void login(BaseEntity<UserEntity> baseEntity);
    }

    abstract class LoginPresenter extends BasePresenter<Model, LoginView> {

        public abstract void login(UserRequest request);


    }

    abstract class RegisterPresenter extends BasePresenter<Model, RegisterView> {


        public abstract void register(UserRequest request);

        public abstract void valicode(UserRequest request);

    }
    abstract class ForgetPresenter extends BasePresenter<Model, ForgetView> {


        public abstract void forget(UserRequest request);

        public abstract void valicode(UserRequest request);

    }
}
