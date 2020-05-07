package com.example.fanzhiyong20200506.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 * RetroiftUtil网络工具类
 * 范智勇第三周周考技能
 */
public class RetroiftUtil {
    //RetroiftUtil单例模式
    private static RetroiftUtil INSTANCE = new RetroiftUtil();
    private final Retrofit retrofit;

    private RetroiftUtil(){
        //创建OkHttp的日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //设置BODY
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置OkHttp建造者模式
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        //设置RetroiftUtil建造者模式
        retrofit = new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com/")//拼接接口的前半段
                .addConverterFactory(GsonConverterFactory.create())//使用Gson来解析该订单数据
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//交给RxJava处理后续结果
                .client(okHttpClient)
                .build();
    }

    public static RetroiftUtil getInstance(){
        return INSTANCE;
    }

    //网络判断
    public static boolean isNetActivice(Context context){
        ConnectivityManager coms = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = coms.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }

    //RetroiftUtil回调
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
