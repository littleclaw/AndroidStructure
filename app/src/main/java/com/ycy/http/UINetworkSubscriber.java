package com.ycy.http;

import android.content.Context;

import com.ycy.widget.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UINetworkSubscriber<BaseRespondBean> implements Observer<BaseRespondBean> {
    private LoadingDialog loadingDialog;

    public UINetworkSubscriber(Context context){
        loadingDialog = new LoadingDialog(context);
    }

    @Override
    public void onSubscribe(Disposable d) {
        loadingDialog.show();
    }

    @Override
    public void onNext(BaseRespondBean baseRespondBean) {

    }

    @Override
    public void onError(Throwable e) {
        loadingDialog.dismiss();
    }

    @Override
    public void onComplete() {
        loadingDialog.dismiss();
    }
}
