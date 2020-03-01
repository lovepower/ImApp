package com.liuguilin.framework.manager;

import android.util.Log;

import com.google.gson.Gson;
import com.liuguilin.framework.entity.StatusCode;
import com.liuguilin.framework.utils.LogUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpManager {
    private static volatile HttpManager mInstnce = null;
    private OkHttpClient mOkHttpClient;
    private String register_url ="http://192.168.198.1:82/auth/register";
    private String login_url = "http://192.168.198.1:82/auth/login";

    private HttpManager() {
        mOkHttpClient = new OkHttpClient();
    }

    public static HttpManager getInstance() {
        if (mInstnce == null) {
            synchronized (HttpManager.class) {
                if (mInstnce == null) {
                    mInstnce = new HttpManager();
                }
            }
        }
        return mInstnce;
    }
public StatusCode register(String tele,String pwd){
    Gson gson = new Gson();
    FormBody.Builder builder = new FormBody.Builder();
    builder.add("tele",tele);
    builder.add("password",pwd);
    RequestBody requestBody = builder.build();
    Request request = new Request.Builder()
            .url(register_url)
            .post(requestBody)
            .build();
    try {
         String result = mOkHttpClient.newCall(request).execute().body().string();
        LogUtils.i(result);
        StatusCode code = gson.fromJson(result,StatusCode.class);
         return code;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}
    public StatusCode login(String tele,String pwd){
        Gson gson = new Gson();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("tele",tele);
        builder.add("password",pwd);
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(login_url)
                .post(requestBody)
                .build();
        try {
            String result = mOkHttpClient.newCall(request).execute().body().string();
            LogUtils.i(result);
            StatusCode code = gson.fromJson(result,StatusCode.class);
            return code;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
