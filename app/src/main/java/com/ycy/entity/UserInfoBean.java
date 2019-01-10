package com.ycy.entity;

import com.ycy.utils.StringUtils;

public class UserInfoBean {
    private String phone;
    private String nickName;
    private String avatarUrl;
    private String balanceRMB;
    private String balanceDKB;

    public boolean isLogin(){
        return !StringUtils.isEmpty(phone);
    }

}
