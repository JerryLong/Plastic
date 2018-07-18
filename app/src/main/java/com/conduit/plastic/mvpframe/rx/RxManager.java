package com.conduit.plastic.mvpframe.rx;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 用于管理RxBus的事件和Rxjava相关代码的生命周期处理
 */
public class RxManager {

    public RxBus mRxBus = RxBus.$();
    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理观察源
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();// 管理订阅者者

    public static final ObservableTransformer IO_TRANSFORMER = new ObservableTransformer() {
        @Override
        public ObservableSource apply(io.reactivex.Observable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static final <T> ObservableTransformer<T, T> applySchedulers(ObservableTransformer transformer) {
        return (ObservableTransformer<T, T>) transformer;
    }

    public void on(String eventName, Consumer<Object> consumer) {
        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        mCompositeDisposable
                .add(mObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }));
    }

    public void add(Disposable m) {
        mCompositeDisposable.add(m);
    }

    public void clear() {
        mCompositeDisposable.clear();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet())
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
    }

    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }


}
