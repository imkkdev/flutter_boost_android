package com.flutter.app.hybrid.plugin;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * Flutter插件示例
 */
public class BatteryPlugin implements MethodChannel.MethodCallHandler {


    public static void register(BinaryMessenger messenger) {
        final MethodChannel channel = new MethodChannel(messenger, "samples.flutter.dev/battery");
        channel.setMethodCallHandler(new BatteryPlugin());
    }

    @Override
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        if (call.method.equals("getBatteryLevel")) {
            result.success("full");
        } else {
            result.notImplemented();
        }
    }
}
