package com.ycy.http.api;

import com.ycy.entity.BaseRespondBean;
import com.ycy.entity.UserInfoBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {

    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseRespondBean<UserInfoBean>> login(@Field("username") String userName, @Field("password") String password);

    @POST("user/add")
    @FormUrlEncoded
    Observable<BaseRespondBean<UserInfoBean>> register(@Field("phone") String phone, @Field("code") String code);

    @GET("register/getCode")
    Observable<BaseRespondBean> getValidationCode();

    Observable<BaseRespondBean<UserInfoBean>> editUserInfo();

    @Multipart
    @POST("user/upload")
    Observable<BaseRespondBean<UserInfoBean>> uploadFile(@Part() RequestBody file);

}
