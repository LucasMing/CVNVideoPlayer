package org.cvn.videoplayer.ui.home.presenter;

import android.app.Activity;

import org.cvn.videoplayer.ui.home.contract.HomeContract;

import io.reactivex.disposables.CompositeDisposable;


/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/3/18
 * 描    述：Home主页面
 * 修订历史：
 * ================================================
 */
public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private CompositeDisposable mSubscriptions;
    private Activity activity;

    public HomePresenter(HomeContract.View androidView) {
        this.mView = androidView;
    }

    @Override
    public void subscribe() {

    }


    @Override
    public void unSubscribe() {
        if(activity!=null){
            activity = null;
        }
    }


}
