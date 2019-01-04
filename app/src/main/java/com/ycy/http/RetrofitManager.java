package com.ycy.http;

import android.os.Environment;

import com.ycy.baseapp.base.YcyApplication;
import com.ycy.http.interceptor.LoggingInterceptor;
import com.ycy.ui.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitManager {
    //OkHttp-- 30秒内直接读缓存
    private static final long HAVE_NET_MAX = 30; //30秒  有网超时时间
    //OkHttp--设置链接与写入超时时间
    private static final int CONNECT_TIMEOUT = 10;

    //定义同步的OkHttpClient
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    //定义RetrofitManager
    private static RetrofitManager instance = null;

    //配置okhttp缓存
    private Cache initOkHttpCache() {
        int cacheSize = 1024 * 1024 * 20; //缓存大小20Mb
        //储存目录 指定缓存路径
        File directoryFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/okHttpCache");
        return new Cache(directoryFile, cacheSize);
    }

    //创建及配置Retrofit
    private RetrofitManager() {
        //创建OkHttpClient并配置
        mOkHttpClient = new OkHttpClient.Builder()
                .cache(initOkHttpCache()) //配置缓存
                .addInterceptor(new LoggingInterceptor()) //添加拦截器
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HAVE_NET_MAX, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.HTTP_BASE)
                .client(mOkHttpClient) //设置自定义配置的OkHttpClient
                .addConverterFactory(GsonConverterFactory.create()) //添加json解析
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加RxJava2的适配
                .build();

    }

    //获取retrofit
    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
