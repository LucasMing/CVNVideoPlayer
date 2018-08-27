package org.cvn.videoplayer.ui.movie.contract;


import org.cvn.videoplayer.base.mvp1.BaseView;
import org.cvn.videoplayer.ui.movie.model.MovieDetailBean;
import org.cvn.videoplayer.base.mvp1.BasePresenter;

/**
 * Description:
 * Update:2018/1/2
 * CreatedTime:2017/12/29
 * Author:yc
 */

public interface MovieDetailContract {

    interface View extends BaseView {
        void setAdapterData(MovieDetailBean movieDetailBean);
        void setError();
        void setEmptyView();
    }

    interface Presenter extends BasePresenter {
        void getData(String dataId);
    }


}
