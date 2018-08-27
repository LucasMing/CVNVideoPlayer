package org.cvn.videoplayer.ui.movie.presenter;

import android.app.Activity;

import org.cvn.videoplayer.ui.movie.model.MovieBean;
import org.cvn.videoplayer.util.LogUtils;
import org.cvn.videoplayer.api.http.movie.MovieModel;
import org.cvn.videoplayer.ui.movie.contract.MovieContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Description:
 * Update:2018/1/2
 * CreatedTime:2017/12/29
 * Author:yc
 */

public class MoviePresenter implements MovieContract.Presenter {

    private static final String TAG = "MoviePresenter";
    private MovieContract.View mView;
    private CompositeDisposable mSubscriptions;
    private Activity activity;

    public MoviePresenter(MovieContract.View androidView) {
        this.mView = androidView;
        mSubscriptions = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }


    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
        if(activity!=null){
            activity = null;
        }
    }

    @Override
    public void getData() {
        MovieModel model = MovieModel.getInstance();
        Disposable subscribe = model.getHomePage()
                //线程控制（也称为调度 / 切换），即讲解功能性操作符中的：subscribeOn（） & observeOn（）
                //功能性操作符subscribeOn（） & observeOn（）作用
                //线程控制，即指定 被观察者 （Observable） / 观察者（Observer） 的工作线程类型
                // 1. 指定被观察者 生产事件的线程
                .subscribeOn(Schedulers.io())
                // 2. 指定观察者 接收 & 响应事件的线程
                // 第一次指定观察者线程 = 主线程
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<MovieBean>() {
                    @Override
                    public void accept(MovieBean movieBean) throws Exception {
                        LogUtils.e(TAG+"----"+"doOnNext accept(MovieBean movieBean)");
                    }
                })
                // 3. 最后再通过订阅（subscribe）连接观察者和被观察者
                .subscribe(new Consumer<MovieBean>() {
                    @Override
                    public void accept(MovieBean movieBean) throws Exception {
                        LogUtils.e(TAG+"----"+"accept(MovieBean movieBean)");
                        if (movieBean != null && movieBean.getRet() != null) {
                            mView.setAdapterData(movieBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.e(TAG+"----"+"accept(Throwable throwable)");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.e(TAG+"----"+"run()");
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        LogUtils.e(TAG+"----"+"accept(Disposable disposable)");
                    }
                });
        mSubscriptions.add(subscribe);
    }



}
