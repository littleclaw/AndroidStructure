package com.ycy.http.api;

import com.ycy.entity.BaseResponse;
import com.ycy.entity.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAPI {
    int VALIDATION_CODE_TYPE_REGISTER = 0;
    int VALIDATION_CODE_TYPE_LOGIN = 1;

    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<UserInfo>> loginWithPwd(@Field("phone") String phone, @Field("password") String password);

    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<UserInfo>> loginWithCode(String phone, String validationCode);

    @POST("user/resetPwd")
    @FormUrlEncoded
    Observable<BaseResponse> resetPwd(String oldPwd, String pwd);

    @POST("user/add")
    @FormUrlEncoded
    Observable<BaseResponse<UserInfo>> register(@Field("phone") String phone, @Field("code") String code);

    @GET("user/register/getCode")
    Observable<BaseResponse> getValidationCode(String phone, int type);

    Observable<BaseResponse<UserInfo>> editUserInfo(String nickName);

    @Multipart
    @POST("user/upload")
    Observable<BaseResponse> uploadAvatar(@Part() RequestBody file);

}
