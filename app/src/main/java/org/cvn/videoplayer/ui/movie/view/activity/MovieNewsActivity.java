package org.cvn.videoplayer.ui.movie.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.blankj.utilcode.util.Utils;

import org.cvn.videoplayer.ui.movie.view.fragment.MovieNewsFragment;
import org.cvn.videoplayer.R;
import org.cvn.videoplayer.base.BasePagerAdapter;
import org.cvn.videoplayer.base.mvp1.BaseActivity;
import org.cvn.videoplayer.util.SettingUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

/**
 * Created by yc on 2018/3/6.
 *
 */

public class MovieNewsActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
    }

    @Override
    public int getContentView() {
        return R.layout.base_tab_view;
    }

    @Override
    public void initView() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        String[] movieNews = Utils.getContext().getResources().getStringArray(R.array.movie_news);
        ArrayList<String> title = new ArrayList<>();
        for (int i = 0; i < movieNews.length; i++) {
            Fragment fragment = MovieNewsFragment.newInstance(movieNews[i]);
            fragmentList.add(fragment);
            title.add(movieNews[i]);
        }
        BasePagerAdapter adapter = new BasePagerAdapter(getSupportFragmentManager(), fragmentList, title);
        viewPager.setAdapter(adapter);
    }

}
