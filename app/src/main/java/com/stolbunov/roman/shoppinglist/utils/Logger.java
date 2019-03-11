package com.stolbunov.roman.shoppinglist.utils;

import android.util.Log;

public class Logger {
    public static synchronized void log(String tag, Throwable t) {
        Log.e(tag, t.getMessage());
    }
}
