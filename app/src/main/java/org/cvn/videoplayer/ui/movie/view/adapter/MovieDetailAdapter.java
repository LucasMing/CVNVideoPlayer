package org.cvn.videoplayer.ui.movie.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.viewHolder.BaseViewHolder;
import org.cvn.videoplayer.R;
import org.cvn.videoplayer.ui.movie.model.MovieDetailBean;
import org.cvn.videoplayer.util.ImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MovieDetailAdapter extends RecyclerArrayAdapter<MovieDetailBean.RetBean.ListBean.ChildListBean> {

    public MovieDetailAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(parent);
    }

    public class MovieViewHolder extends BaseViewHolder<MovieDetailBean.RetBean.ListBean.ChildListBean> {


        @Bind(R.id.iv_movie_photo)
        ImageView ivMoviePhoto;
        @Bind(R.id.tv_movie_title)
        TextView tvMovieTitle;
        @Bind(R.id.tv_movie_directors)
        TextView tvMovieDirectors;
        @Bind(R.id.tv_movie_casts)
        TextView tvMovieCasts;
        @Bind(R.id.tv_movie_genres)
        TextView tvMovieGenres;

        MovieViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_movie_detail_news);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void setData(MovieDetailBean.RetBean.ListBean.ChildListBean data) {
            super.setData(data);
            if (data != null) {
                ImageUtil.loadImgByPicasso(getContext(), data.getPic(), R.drawable.image_default, ivMoviePhoto);
                tvMovieTitle.setText(data.getTitle());
                tvMovieDirectors.setText("潇湘剑雨");
                tvMovieCasts.setText("时间"+data.getAirTime());
                tvMovieGenres.setText(data.getDescription());
            }
        }


    }


}
