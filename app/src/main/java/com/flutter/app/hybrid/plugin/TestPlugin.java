package com.flutter.app.hybrid.plugin;

import android.util.Log;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * 插件注册方式二
 *
 * 参考Flutter github 示例：
 * https://github.com/flutter/plugins/blob/master/packages/battery/android/src/main/java/io/flutter/plugins/battery/BatteryPlugin.java
 */
public class TestPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin {

    public static final String TAG = "TestPlugin";

    MethodChannel channel;

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Log.d(TAG, "onMethodCall: " + call.method + " > " + call.arguments);
    }

    @Override
    public void onAttachedToEngine(FlutterPluginBinding binding) {
        Log.d(TAG, "onAttachedToEngine: ");
        channel = new MethodChannel(binding.getBinaryMessenger(), "samples.flutter.dev/battery2");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding binding) {
        Log.d(TAG, "onDetachedFromEngine: ");
        channel.setMethodCallHandler(null);
    }
}
