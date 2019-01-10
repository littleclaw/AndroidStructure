package com.ycy.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.ycy.baseapp.base.BaseActivity;
import com.ycy.route.PageRouter;
import com.ycy.ui.R;
import com.ycy.utils.StringUtils;
import com.ycy.utils.ToastUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class RegisterActivity extends BaseActivity {
    private EditText mPhoneEt;
    private EditText mCodeEt;
    private Button mGetCodeBtn;
    private Button mRegisterBtn;
    private CheckBox mAgreeContractCbx;
    private TextView mContractTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews(){
        mPhoneEt = findViewById(R.id.register_phone_et);
        mCodeEt = findViewById(R.id.register_code_et);
        mGetCodeBtn = findViewById(R.id.get_validation_code_btn);
        mRegisterBtn = findViewById(R.id.register_btn);
        mAgreeContractCbx = findViewById(R.id.register_agree_contract_cbx);
        mContractTv = findViewById(R.id.register_contract_detail_tv);

        mGetCodeBtn.setOnClickListener(codeBtn -> getCodeWithCoolDown());
        mRegisterBtn.setOnClickListener(registerBtn -> {
            if(validateInput()){
                registerLogin(mPhoneEt.getText().toString(), mCodeEt.getText().toString());
            }
        });
        mAgreeContractCbx.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mRegisterBtn.setEnabled(isChecked);
        });
        mContractTv.setOnClickListener(contractTv -> PageRouter.routRegisterContract(this));
    }

    private void getCodeWithCoolDown(){
        mGetCodeBtn.setEnabled(false);
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
        if(StringUtils.isEmpty(mPhoneEt.getText()) || StringUtils.isEmpty(mCodeEt.getText())){
            ToastUtils.showShortToast("手机号或验证码不能为空");
            return false;
        }

        return true;
    }

    private void registerLogin(String phone, String code){

    }
}

