package com.tiptap.tda_user.tiptap.common;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.tiptap.tda_user.tiptap.di.component.AppComponent;
import com.tiptap.tda_user.tiptap.di.component.DaggerAppComponent;
import com.tiptap.tda_user.tiptap.di.module.AppModule;
import com.tiptap.tda_user.tiptap.main.activity.DB.LruBitmapCache;

public class SampleApp extends Application {

    public static final boolean ENCRYPTED = true;

    public static SampleApp get(Context context) {
        return (SampleApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
        mInstance = this;
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/IRANSansWeb.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
    }

    private AppComponent appComponent;

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static final String TAG = SampleApp.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static SampleApp mInstance;

    public static synchronized SampleApp getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}