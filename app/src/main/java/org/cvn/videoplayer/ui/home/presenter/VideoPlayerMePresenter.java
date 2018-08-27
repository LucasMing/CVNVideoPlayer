package org.cvn.videoplayer.ui.home.presenter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import org.cvn.videoplayer.ui.home.contract.VideoPlayerMeContract;
import org.cvn.videoplayer.ui.home.model.VideoPlayerComment;
import org.cvn.videoplayer.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;


/**
 * ================================================
 * 版    本：1.0
 * 创建日期：2017/3/18
 * 描    述：Main主页面
 * 修订历史：
 * ================================================
 */
public class VideoPlayerMePresenter implements VideoPlayerMeContract.Presenter {

    private VideoPlayerMeContract.View mView;
    private CompositeDisposable mSubscriptions;
    private Activity activity;

    public VideoPlayerMePresenter(VideoPlayerMeContract.View androidView) {
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
    public void bindView(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void getData() {
        List<VideoPlayerComment> comments = new ArrayList<>();
        for(int a=0 ; a<20 ; a++){
            VideoPlayerComment videoPlayerComment = new VideoPlayerComment(
                    R.drawable.battery_10,"仿优酷",5,"仿优酷视频Ui太好呢");
            comments.add(videoPlayerComment);
        }
        mView.setAdapterView(comments);
    }

    /**
     * 开始下载
     */
    @Override
    public void startDownload() {

    }
}
