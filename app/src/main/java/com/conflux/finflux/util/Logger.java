package com.conflux.finflux.util;

import android.util.Log;

/**
 * Created by praveen on 6/23/2016.
 */
public class Logger {
    private static boolean isLogVisible = true;

    public static void i(String tag,String message)
    {
        if(isLogVisible) {
            Log.i(tag, message);
        }
    }
    public static void d(String tag,String message)
    {
        if(isLogVisible) {
            Log.d(tag, message);
        }
    }
    public static void e(String tag,String message)
    {
        if(isLogVisible) {
            Log.e(tag, message);

        }
    }
    public static void v(String tag,String message)
    {
        if(isLogVisible) {
            Log.v(tag, message);
        }
    }
}
