package org.cvn.videoplayer.ui.home.contract;


import android.support.v7.app.AppCompatActivity;

import org.cvn.videoplayer.base.mvp1.BasePresenter;
import org.cvn.videoplayer.base.mvp1.BaseView;
import org.cvn.videoplayer.ui.home.model.VideoPlayerComment;

import java.util.List;

/**
 * ================================================
 * 作    者：杨充
 * 版    本：1.0
 * 创建日期：2017/3/18
 * 描    述：
 * 修订历史：
 * ================================================
 */
public interface VideoPlayerMeContract {

    //View(activity/fragment)继承，需要实现的方法
    interface View extends BaseView {
        void setAdapterView(List<VideoPlayerComment> comments);
    }

    //Presenter控制器
    interface Presenter extends BasePresenter {
        void bindView(AppCompatActivity activity);
        void getData();
        void startDownload();
    }


}
