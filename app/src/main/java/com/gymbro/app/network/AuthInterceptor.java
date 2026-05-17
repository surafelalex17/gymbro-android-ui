package com.gymbro.app.network;

import android.content.Context;

import androidx.annotation.NonNull;

import com.gymbro.app.storage.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private TokenManager tokenManager;

    public AuthInterceptor(Context context) {
        tokenManager = new TokenManager(context);
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        String token = tokenManager.getToken();

        Request request = chain.request();

        if (token != null) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
        }

        return chain.proceed(request);
    }
}