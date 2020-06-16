package com.flutter.app.hybrid.util;

import android.content.Context;

import io.flutter.embedding.android.FlutterActivity;

/**
 * Kotlin 跳转 FlutterActivity
 */
public class StartFlutterProxy {

    // 跳转Flutter官方Activity方式，fix kotlin代码里报错的问题，抽象在java方法

    // Kotlin使用FlutterActivity.withNewEngine() 会报错：
    // Cannot access 'android.arch.lifecycle.LifecycleOwner' which is a supertype of 'io.flutter.embedding.android.FlutterActivity'.
    // Check your module classpath for missing or conflicting
    public static void startActivity(Context context, String initialRoute) {
        context.startActivity(
                FlutterActivity
                        .withNewEngine()
                        .initialRoute(initialRoute)
                        .build(context)
        );
    }

}
