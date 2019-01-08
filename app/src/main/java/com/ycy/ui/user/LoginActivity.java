package com.ycy.ui.user;

import android.os.Bundle;

import com.ycy.baseapp.base.BaseTitledActivity;
import com.ycy.ui.R;

public final class LoginActivity extends BaseTitledActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(R.layout.activity_user_login);
    }
}
