package org.cvn.videoplayer.api.http.news;


import org.cvn.videoplayer.api.manager.RetrofitWrapper;
import org.cvn.videoplayer.ui.video.model.bean.MultiNewsArticleBean;

import io.reactivex.Observable;


/**
 * Created by PC on 2017/8/21.
 * 作者：PC
 */

public class NewsModel {

    private static NewsModel model;
    private NewsApi mApiService;
    private static final String HOST = "http://toutiao.com/";

    private NewsModel() {
        mApiService = RetrofitWrapper
                .getInstance(HOST)
                .create(NewsApi.class);
    }

    public static NewsModel getInstance(){
        if(model == null) {
            model = new NewsModel();
        }
        return model;
    }

    public Observable<MultiNewsArticleBean> getNewsArticle(String category, String maxBehotTime) {
        return mApiService.getNewsArticle(category,maxBehotTime);
    }

    public Observable<MultiNewsArticleBean> getNewsArticle2(String category, String maxBehotTime) {
        return mApiService.getNewsArticle2(category,maxBehotTime);
    }

}
