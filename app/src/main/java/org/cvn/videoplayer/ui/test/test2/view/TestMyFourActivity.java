package org.cvn.videoplayer.ui.test.test2.view;

import org.cvn.videoplayer.R;
import org.cvn.videoplayer.base.mvp1.BaseActivity;
import org.yczbj.ycvideoplayerlib.VideoPlayerManager;


public class TestMyFourActivity extends BaseActivity  {

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) return;
        super.onBackPressed();
    }


    @Override
    public int getContentView() {
        return R.layout.activity_test_my_four;
    }

    @Override
    public void initView() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new TestMyFourFragment())
                .commit();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

}
