package com.ycy.http.api;

import com.ycy.entity.UserInfoBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {

    @POST("ac/login")
    @FormUrlEncoded
    Observable<UserInfoBean> login(@Field("username") String userName, @Field("password") String password);

}
