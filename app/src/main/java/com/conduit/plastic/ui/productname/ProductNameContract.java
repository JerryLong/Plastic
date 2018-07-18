package com.conduit.plastic.ui.productname;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.ProductNameAllEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public interface ProductNameContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<ProductNameAllEntity>> productName();
    }


    interface View extends BaseView {
        void productNameList(ProductNameAllEntity baseEntity);

    }


    abstract class ProductNamePresenter extends BasePresenter<Model, View> {
        public abstract void productName();
    }
}
