package org.cvn.videoplayer.ui.movie.contract;


import org.cvn.videoplayer.base.mvp1.BaseView;
import org.cvn.videoplayer.ui.movie.model.MovieBean;
import org.cvn.videoplayer.base.mvp1.BasePresenter;

/**
 * Description:
 * Update:2018/1/2
 * CreatedTime:2017/12/29
 * Author:yc
 */

public interface MovieContract {

    interface View extends BaseView {
        void setAdapterData(MovieBean movieBean);
    }

    interface Presenter extends BasePresenter {
        void getData();
    }


}
