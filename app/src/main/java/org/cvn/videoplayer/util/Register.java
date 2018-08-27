package org.cvn.videoplayer.util;

import android.support.annotation.NonNull;

import org.cvn.videoplayer.model.LoadingBean;
import org.cvn.videoplayer.model.binder.LoadingViewBinder;
import org.cvn.videoplayer.ui.news.model.binder.NewsArticleImgViewBinder;
import org.cvn.videoplayer.ui.news.model.binder.NewsArticleTextViewBinder;
import org.cvn.videoplayer.ui.video.model.bean.MultiNewsArticleDataBean;
import org.cvn.videoplayer.model.LoadingEndBean;
import org.cvn.videoplayer.model.binder.LoadingEndViewBinder;
import org.cvn.videoplayer.ui.video.model.binder.NewsArticleVideoViewBinder;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by yc on 2018/2/26.
 */

public class Register {

    public static void registerVideoArticleItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class, new NewsArticleVideoViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    public static void registerNewsArticleItem(MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class)
                .to(new NewsArticleImgViewBinder(),
                        new NewsArticleVideoViewBinder(),
                        new NewsArticleTextViewBinder())
                .withClassLinker(new ClassLinker<MultiNewsArticleDataBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<MultiNewsArticleDataBean, ?>> index(int position, @NonNull MultiNewsArticleDataBean item) {
                        if (item.isHas_video()) {
                            return NewsArticleVideoViewBinder.class;
                        }
                        if (null != item.getImage_list() && item.getImage_list().size() > 0) {
                            return NewsArticleImgViewBinder.class;
                        }
                        return NewsArticleTextViewBinder.class;
                    }
                });
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
