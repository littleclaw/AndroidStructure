package com.ycy.ui.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.ycy.baseapp.base.BaseActivity;
import com.ycy.entity.BaseRespondBean;
import com.ycy.entity.UserInfoBean;
import com.ycy.http.RetrofitManager;
import com.ycy.http.api.UserService;
import com.ycy.ui.R;
import com.ycy.utils.SPUtils;
import com.ycy.utils.StringUtils;
import com.ycy.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public final class LoginActivity extends BaseActivity {
    private EditText mPhoneNumEt;
    private EditText mValidationCodeEt;
    private Button mGetCodeBtn;
    private Button mLoginBtn;

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
        mGetCodeBtn = findViewById(R.id.get_validation_code_btn);
        mLoginBtn = findViewById(R.id.login_btn);

        mGetCodeBtn.setOnClickListener(codeBtn -> getCodeWithCoolDown());
        mLoginBtn.setOnClickListener(loginBtn -> {
            if(validateInput()){
                doLogin();
            }
        });
    }

    private void getCodeWithCoolDown(){
        mGetCodeBtn.setEnabled(false);
        RetrofitManager.getInstance().create(UserService.class).getValidationCode()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(baseRespondBean -> {

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
        RetrofitManager.getInstance().create(UserService.class)
                .login(phoneNum, code)
                .map(userInfoBeanBaseRespondBean -> userInfoBeanBaseRespondBean.replydata)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new Consumer<UserInfoBean>() {
                    @Override
                    public void accept(UserInfoBean userInfoBean) throws Exception {
                        if(userInfoBean.isLogin()){
                            setResult(RESULT_OK);
                            SPUtils.putUserInfo(new Gson().toJson(userInfoBean));
                            finish();
                        }else {
                            ToastUtils.showShortToast("登录失败");
                        }
                    }
                });
    }

}
