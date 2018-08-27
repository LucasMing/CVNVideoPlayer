package org.cvn.videoplayer.ui.video.contract;

import org.cvn.videoplayer.ui.video.model.bean.MultiNewsArticleDataBean;
import org.cvn.videoplayer.base.mvp2.IBaseListView;
import org.cvn.videoplayer.base.mvp2.IBasePresenter;

import java.util.List;

public interface IVideoArticle {

    interface View extends IBaseListView<Presenter> {
        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {
        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);
    }
}
