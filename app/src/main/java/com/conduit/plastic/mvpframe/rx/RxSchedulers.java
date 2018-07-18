package com.conduit.plastic.mvpframe.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by baixiaokang on 16/5/6.
 */
public class RxSchedulers {
    public static final ObservableTransformer IO_TRANSFORMER = new ObservableTransformer() {
        @Override public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io());
        }
    };
    public   <T> ObservableTransformer<T, T> applySchedulers(ObservableTransformer transformer){
        return (ObservableTransformer<T, T>)transformer;
    }
    public static <T> ObservableTransformer<T,T> io_main() {

        return new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
