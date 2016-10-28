package com.buybuybuy.http;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016-9-19.
 */
public class OkHttpClientUtils {
    private static OkHttpClient okHttpClient;
    private static OkHttpClientUtils okHttpClientUtils;

    //1.
    private OkHttpClientUtils(Context context) {
        okHttpClient = getOkHttpClient();
        //设置
        okHttpClient.newBuilder().connectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.newBuilder().writeTimeout(20, TimeUnit.SECONDS);
        okHttpClient.newBuilder().readTimeout(15, TimeUnit.SECONDS);
        //设置缓存大小
        int size = 1024 * 1024 * 10;//10M
        Cache cache = new Cache(context.getCacheDir(), size);
        okHttpClient.newBuilder().cache(cache);
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                okHttpClient = new OkHttpClient();
            }
        }
        return okHttpClient;
    }

    //
    public static OkHttpClientUtils getOkHttpClientUtils(Context context) {

        if (okHttpClientUtils == null) {
            synchronized (OkHttpClientUtils.class) {
                okHttpClientUtils = new OkHttpClientUtils(context);
            }
        }
        return okHttpClientUtils;
    }
    ///////////////////////////////////////////////////////////////////////////
    // GET同步网络请求
    ///////////////////////////////////////////////////////////////////////////

    private Request getRequest(String url) {
        Request request = new Request.Builder().url(url).build();
        return request;
    }

    private Response getResponse(String url) throws IOException {
        Request request = getRequest(url);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    private ResponseBody getResponseBody(String url) throws IOException {
        Response response = getResponse(url);
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            return body;
        }
        return null;
    }
//返回值为String类型
    public static String getStringFromUrl(String url) throws IOException {
        ResponseBody body = okHttpClientUtils.getResponseBody(url);
        if (body != null){
            String result = body.string();
            return result;
        }
        return null;
    }
    //返回值为byte[]类型
    public static byte[] getBytesFromUrl(String url) throws IOException {
        ResponseBody body = okHttpClientUtils.getResponseBody(url);
        if (body != null){
            byte[] bytes = body.bytes();
            return bytes;
        }
        return null;
    }
    //返回值为字节流类型
    public static InputStream getStreamFromUrl(String url) throws IOException {
        ResponseBody body = okHttpClientUtils.getResponseBody(url);
        if (body != null){
            InputStream stream = body.byteStream();
            return stream;
        }
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // GET异步网络请求
    ///////////////////////////////////////////////////////////////////////////

//    public static void getDataAsync(String url, Context context, Callback callback){
//        Request request = getOkHttpClientUtils(context).getRequest(url);
//        okHttpClient.newCall(request).enqueue(callback);
//    }
}
