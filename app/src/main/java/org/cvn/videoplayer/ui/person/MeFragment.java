package org.cvn.videoplayer.ui.person;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.cvn.videoplayer.base.mvp1.BaseFragment;
import org.cvn.videoplayer.ui.main.view.activity.MainActivity;
import org.cvn.videoplayer.weight.AboutMeDialog;
import org.cvn.videoplayer.R;

import butterknife.Bind;

/**
 * Description:
 * Update:
 * CreatedTime:2017/12/29
 * Author:MysteryCode
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tv_3)
    TextView tv3;
    private MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        tv3.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_3:
                //new AboutMeDialog(activity).show();
                break;
            default:
                break;
        }
    }


}
