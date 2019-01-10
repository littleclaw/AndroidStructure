package com.ycy.baseapp.base;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ycy.ui.user.LoginActivity;
import com.ycy.utils.SPUtils;
import com.ycy.utils.ToastUtils;

import static com.ycy.route.PageRouter.LOGIN_REQUEST_CODE;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == LOGIN_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                onUserLogin();
            }else {
                ToastUtils.showShortToast("取消登录");
            }
        }
    }

    protected void onUserLogin(){
    }

    public boolean isLogin(){
        return SPUtils.isLogin();
    }

}
