package me.yangchao.whatson;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import me.yangchao.whatson.net.OkHttp3Stack;

/**
 * Created by richard on 4/10/17.
 */

public class App extends Application {

    public static final String LOG_TAG = "BoomCast";

    private static App APP;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;

        // initialize Sugar ORM
//        SugarContext.init(this);

        // volley
        mRequestQueue = Volley.newRequestQueue(this, new OkHttp3Stack());
        mRequestQueue.start();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // destroy Sugar ORM
//        SugarContext.terminate();

        mRequestQueue.stop();
        mRequestQueue = null;

    }

    public static App getInstance() { return APP; }

    public static Application getApplication() {
        return APP;
    }

    public static Context getContext() {
        return APP;
    }

    public RequestQueue getVolleyRequestQueue()
    {
        return mRequestQueue;
    }

    private static void addRequest(@NonNull
                                   final Request<?> request) {
        APP.getVolleyRequestQueue().add(request);
    }

    public static void addRequest(@NonNull
                                  final Request<?> request, @NonNull
                                  final String tag) {
        request.setTag(tag);
        addRequest(request);
    }

    public static void cancelAllRequests(@NonNull
                                         final String tag) {
        APP.getVolleyRequestQueue().cancelAll(tag);
    }

}
