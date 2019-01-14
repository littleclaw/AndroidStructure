package com.ycy.ui.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ycy.entity.UserInfo;
import com.ycy.route.PageRouter;
import com.ycy.ui.R;
import com.ycy.utils.SPUtils;

public final class UserFragment extends Fragment {
    private RelativeLayout mLoginRl;
    private ImageView mAvatarIv;
    private TextView mNickNameTv;
    private TextView mUserInfoTv;
    private TextView mBanlanceRMBTv;
    private TextView mBanlanceDKBTv;
    private Button mBalanceDetailBtn;
    private Button mCashDetailBtn;
    private Button mCashNowBtn;

    private UserInfo mUserInfo;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        initViews(v);
        mUserInfo = SPUtils.getSPUserInfo();
        if(mUserInfo.isLogin()){
            setUpUserPage();
        }
        return v;
    }

    private void initViews(View v){
        mLoginRl = v.findViewById(R.id.login_rl);
        mAvatarIv = v.findViewById(R.id.user_avatar_iv);
        mNickNameTv = v.findViewById(R.id.user_nick_name_tv);
        mUserInfoTv = v.findViewById(R.id.user_info_tv);
        mBanlanceRMBTv = v.findViewById(R.id.balance_rmb_tv);
        mBanlanceDKBTv = v.findViewById(R.id.balance_dkb_tv);
        mBalanceDetailBtn = v.findViewById(R.id.balance_detail_btn);
        mCashDetailBtn = v.findViewById(R.id.cash_btn);
        mCashNowBtn = v.findViewById(R.id.cash_now_btn);

        initOnClickListener();
    }

    private void initOnClickListener(){
        View.OnClickListener noLoginOnClickListener = v -> PageRouter.requestLogin(getActivity());
        mLoginRl.setOnClickListener(noLoginOnClickListener);
        //TODO more to go
    }

    private void setUpUserPage(){
        mNickNameTv.setText(mUserInfo.getName());
        mBalanceDetailBtn.setEnabled(true);
        mCashDetailBtn.setEnabled(true);
        mCashNowBtn.setEnabled(true);
    }

}
