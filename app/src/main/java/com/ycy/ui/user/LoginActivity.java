package com.ycy.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.ycy.baseapp.base.BaseActivity;
import com.ycy.entity.BaseResponse;
import com.ycy.entity.UserInfo;
import com.ycy.http.RetrofitManager;
import com.ycy.http.api.UserAPI;
import com.ycy.route.PageRouter;
import com.ycy.ui.R;
import com.ycy.utils.RegexUtils;
import com.ycy.utils.SPUtils;
import com.ycy.utils.StringUtils;
import com.ycy.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public final class LoginActivity extends BaseActivity {
    private final int LOGIN_TYPE_CODE = 0;
    private final int LOGIN_TYPE_PWD = 1;
    private int mLoginType = LOGIN_TYPE_CODE;
    private EditText mPhoneNumEt;
    private EditText mValidationCodeEt;
    private EditText mLoginPwdEt;
    private Button mGetCodeBtn;
    private Button mLoginBtn;
    private Button mResetPwdBtn;
    private TextView mSwitchLoginTypeTv;
    private TextView mRegisterTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews(){
        mPhoneNumEt = findViewById(R.id.login_phone_et);
        mValidationCodeEt = findViewById(R.id.login_code_et);
        mLoginPwdEt = findViewById(R.id.login_pwd_et);
        mGetCodeBtn = findViewById(R.id.get_validation_code_btn);
        mLoginBtn = findViewById(R.id.login_btn);
        mResetPwdBtn = findViewById(R.id.reset_pwd_btn);
        mSwitchLoginTypeTv = findViewById(R.id.switch_login_type_tv);
        mRegisterTv = findViewById(R.id.register_tv);

        mGetCodeBtn.setOnClickListener(codeBtn -> getCodeWithCoolDown());
        mLoginBtn.setOnClickListener(loginBtn -> {
            if(validateInput()){
                doLogin();
            }
        });
        mRegisterTv.setOnClickListener(registerTv -> PageRouter.routeRegister(this));
        mSwitchLoginTypeTv.setOnClickListener(switchLoginTypeTv -> switchLoginType());
    }

    private void getCodeWithCoolDown(){
        mGetCodeBtn.setEnabled(false);
        String phoneNumStr = mPhoneNumEt.getText().toString();
        if(!RegexUtils.isMobileSimple(phoneNumStr)){
            ToastUtils.showShortToast("请输入正确的手机号码");
            return;
        }
        RetrofitManager.getInstance().create(UserAPI.class)
                .getValidationCode(phoneNumStr, UserAPI.VALIDATION_CODE_TYPE_LOGIN)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseRespondBean -> {
                    //TODO
                });
        final int countDownSum = 60;
        Flowable.intervalRange(0, countDownSum, 0,
                1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(ticks -> mGetCodeBtn.setText((countDownSum-ticks) + "秒重新获取"))
                .doOnComplete(() -> {
                    mGetCodeBtn.setEnabled(true);
                    mGetCodeBtn.setText(R.string.get_code);
                })
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe();
    }

    private boolean validateInput(){
        //TODO 提交请求前先验证输入合法性
        if(StringUtils.isEmpty(mPhoneNumEt.getText()) || StringUtils.isEmpty(mValidationCodeEt.getText())){
            ToastUtils.showShortToast("输入不能为空");
            return false;
        }
        return true;
    }

    private void doLogin(){
        String phoneNum = mPhoneNumEt.getText().toString();
        String code = mValidationCodeEt.getText().toString();
        RetrofitManager.getInstance().create(UserAPI.class)
                .loginWithPwd(phoneNum, code)
                .map(BaseResponse::getObj)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {
                        if(userInfo.isLogin()){
                            setResult(RESULT_OK);
                            SPUtils.putUserInfo(new Gson().toJson(userInfo));
                            finish();
                        }else {
                            ToastUtils.showShortToast("登录失败");
                        }
                    }
                });
    }

    private void switchLoginType(){
        if(mLoginType == LOGIN_TYPE_CODE){
            mLoginType = LOGIN_TYPE_PWD;
            mResetPwdBtn.setVisibility(View.VISIBLE);
            mGetCodeBtn.setVisibility(View.INVISIBLE);
            mLoginPwdEt.setVisibility(View.VISIBLE);
            mValidationCodeEt.setVisibility(View.INVISIBLE);
        }else {
            mLoginType = LOGIN_TYPE_CODE;
            mResetPwdBtn.setVisibility(View.INVISIBLE);
            mGetCodeBtn.setVisibility(View.VISIBLE);
            mLoginPwdEt.setVisibility(View.INVISIBLE);
            mValidationCodeEt.setVisibility(View.VISIBLE);
        }
    }

}
