package com.ycy.route;

import android.app.Activity;
import android.content.Intent;

import com.ycy.ui.user.LoginActivity;
import com.ycy.ui.user.RegisterActivity;

public class PageRouter {
    public static final int LOGIN_REQUEST_CODE = 9;

    public static void requestLogin(Activity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivityForResult(intent, LOGIN_REQUEST_CODE);
    }

    public static void routRegisterContract(Activity activity){
        //TODO
//        Intent intent = new Intent(activity, );
//        activity.startActivity(intent);
    }

    public static void routeRegister(Activity activity){
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }
}
