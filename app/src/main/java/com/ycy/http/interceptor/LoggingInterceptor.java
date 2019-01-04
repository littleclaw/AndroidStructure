package com.ycy.http.interceptor;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.ycy.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.d(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        long t1 = System.nanoTime();
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        LogUtils.d(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1)/1e6 , response.headers()));

        return response;
    }
}
