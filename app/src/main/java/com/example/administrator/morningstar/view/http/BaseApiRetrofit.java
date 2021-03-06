package com.example.administrator.morningstar.view.http;



import com.example.administrator.morningstar.view.tool.ClearableCookieJar;
import com.example.administrator.morningstar.view.tool.MsApplication;
import com.example.administrator.morningstar.view.tool.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @创建者 CSDN_LQR
 * @描述 配置Retrofit（配置网络缓存cache、配置持久cookie免登录）
 */

public class BaseApiRetrofit {

    private final OkHttpClient mClient;

    public OkHttpClient getClient() {
        return mClient;
    }

    public BaseApiRetrofit() {
        /*================== common ==================*/

        // Log信息拦截器
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);//这里可以选择拦截级别

        //cache
        File httpCacheDir = new File(MsApplication.getContext().getCacheDir(), "response");
        int cacheSize = 10 * 1024 * 1024;// 10 MiB
        Cache cache = new Cache(httpCacheDir, cacheSize);

        //cookie
        PersistentCookieJar cookieJar =
        new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MsApplication.getContext()));


        //OkHttpClient
        mClient = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_HEADER_CONTROL_INTERCEPTOR)  //请求头信息
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)   //缓存信息
                .addInterceptor(new LoggingInterceptor())
//                .addInterceptor(loggingInterceptor)//设置 Debug Log 模式
                .cache(cache)
                .cookieJar(cookieJar)
                .build();
    }

    //header配置
    Interceptor REWRITE_HEADER_CONTROL_INTERCEPTOR = chain -> {
//        Request request1 = chain.request();
//        HttpUrl url=request1.url().newBuilder()
//                .addQueryParameter("count", "5")
//                .addQueryParameter("start", "0")
//                .build();
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Accept", "*/*")
//                .addHeader("Cookie", "add cookies here")
//                .url(url)
                .build();
        return chain.proceed(request);
    };



    //cache配置
    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {

        //通过 CacheControl 控制缓存数据
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);//这个是控制缓存的最大生命时间
        cacheBuilder.maxStale(365, TimeUnit.DAYS);//这个是控制缓存的过时时间
        CacheControl cacheControl = cacheBuilder.build();

        //设置拦截器
        Request request = chain.request();
        if (!NetUtils.isNetworkAvailable(MsApplication.getContext())) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
        }

        Response originalResponse = chain.proceed(request);
        if (NetUtils.isNetworkAvailable(MsApplication.getContext())) {
            int maxAge = 0;//read from cache
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28;//tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .removeHeader("Prama")
                    .header("Cache-Control", "poublic, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();//收到响应的时间
            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);

            return response;
        }
    }

}
