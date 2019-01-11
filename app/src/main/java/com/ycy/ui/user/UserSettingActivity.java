package com.ycy.ui.user;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ycy.baseapp.base.BaseActivity;
import com.ycy.ui.R;

public class UserSettingActivity extends BaseActivity {
    private RelativeLayout mChangeAvatarRl;
    private RelativeLayout mChangeNickNameRl;
    private RelativeLayout mResetPwdRl;
    private Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initViews(){

    }
}
