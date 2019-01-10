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
import android.widget.TextView;

import com.ycy.entity.UserInfoBean;
import com.ycy.ui.R;
import com.ycy.utils.SPUtils;

public final class UserFragment extends Fragment {
    private ImageView mAvatarIv;
    private TextView mNickNameTv;
    private TextView mUserInfoTv;
    private TextView mBanlanceRMBTv;
    private TextView mBanlanceDKBTv;
    private Button mBanlanceDetailBtn;
    private Button mCashDetailBtn;
    private Button mCashNowBtn;

    private UserInfoBean mUserInfoBean;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        initViews(v);
        mUserInfoBean = SPUtils.getSPUserInfo();
        if(mUserInfoBean.isLogin()){
            setUpUserPage();
        }

        return v;
    }

    private void initViews(View v){
        mAvatarIv = v.findViewById(R.id.user_avatar_iv);
        mNickNameTv = v.findViewById(R.id.user_nick_name_tv);
        mUserInfoTv = v.findViewById(R.id.user_info_tv);
        mBanlanceRMBTv = v.findViewById(R.id.balance_rmb_tv);
        mBanlanceDKBTv = v.findViewById(R.id.balance_dkb_tv);
        mBanlanceDetailBtn = v.findViewById(R.id.balance_detail_btn);
        mCashDetailBtn = v.findViewById(R.id.cash_btn);
        mCashNowBtn = v.findViewById(R.id.cash_now_btn);
    }

    private void setUpUserPage(){
//        mNickNameTv.setText();
    }

}
