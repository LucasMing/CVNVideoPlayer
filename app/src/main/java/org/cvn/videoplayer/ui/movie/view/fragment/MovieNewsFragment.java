package org.cvn.videoplayer.ui.movie.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;

import org.yczbj.ycrefreshviewlib.YCRefreshView;
import org.yczbj.ycrefreshviewlib.adapter.RecyclerArrayAdapter;
import org.yczbj.ycrefreshviewlib.item.RecycleViewItemLine;
import org.cvn.videoplayer.R;
import org.cvn.videoplayer.base.mvp1.BaseLazyFragment;
import org.cvn.videoplayer.ui.movie.view.activity.MovieNewsActivity;
import org.cvn.videoplayer.ui.movie.view.adapter.MovieNewsAdapter;

import butterknife.Bind;

/**
 * Created by yc on 2018/3/6.
 */

public class MovieNewsFragment extends BaseLazyFragment {

    @Bind(R.id.recyclerView)
    YCRefreshView recyclerView;

    private static final String TAG = "MovieNewsFragment";
    private MovieNewsActivity activity;
    private MovieNewsAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MovieNewsActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public static MovieNewsFragment newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        MovieNewsFragment fragment = new MovieNewsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getContentView() {
        return R.layout.base_easy_recycle;
    }


    @Override
    public void initView() {
        initRecyclerView();
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onLazyLoad() {

    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        final RecycleViewItemLine line = new RecycleViewItemLine(activity, LinearLayout.HORIZONTAL,
                SizeUtils.dp2px(1), Color.parseColor("#f5f5f7"));
        recyclerView.addItemDecoration(line);
        adapter = new MovieNewsAdapter(activity);
        recyclerView.setAdapter(adapter);
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SwipeRefreshLayout swipeToRefresh = recyclerView.getSwipeToRefresh();
                if (swipeToRefresh.isRefreshing()) {
                    recyclerView.setRefreshing(false);
                }
            }
        });
    }




}
