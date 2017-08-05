package com.example.dimas.mysooperapp.ui.utils;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.example.dimas.mysooperapp.ui.inflater.Layout;

public final class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("can't create instance of AppUtils class");
    }

    public static Intent withExtras(final Intent intent, final Bundle extras) {
        if (extras != null) {
            intent.putExtras(extras);
        }
        return intent;
    }

    @LayoutRes
    public static int getLayoutId(@NonNull final Object source) {
        final Class clazz = source.getClass();
        if (!clazz.isAnnotationPresent(Layout.class)) {
            return 0;
        }
        final Layout layoutAnnotation = (Layout) clazz.getAnnotation(Layout.class);
        return layoutAnnotation.id();
    }

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
