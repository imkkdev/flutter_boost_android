package com.flutter.app.hybrid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.flutter.app.hybrid.activity.TestActivity;
import com.idlefish.flutterboost.containers.BoostFlutterActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class PageRouter {

    // 需要与flutter端 FlutterBoost.singleton.registerPageBuilders保持一致
    public final static Map<String, String> pageName = new HashMap<String, String>() {{
        put("/first", "/first");
        put("/second", "/second");
        put("/flutterPlugin", "/flutterPlugin");
        put("/flutter2native", "/flutter2native");
        put("sample://flutterPage", "flutterPage");
    }};

    public static final String NATIVE_PAGE_URL = "sample://nativePage";
    public static final String FLUTTER_PAGE_URL = "sample://flutterPage";
    public static final String FLUTTER_FRAGMENT_PAGE_URL = "sample://flutterFragmentPage";

    public static boolean openPageByUrl(Context context, String url, Map params) {
        return openPageByUrl(context, url, params, 0);
    }

    public static boolean openPageByUrl(Context context, String url, Map params, int requestCode) {

        String path = url.split("\\?")[0];

        Log.i("openPageByUrl", path);

        Log.i("openPageByUrl", "context is Activity " + (context instanceof Activity));

        try {
            if (pageName.containsKey(path)) {
                Intent intent = BoostFlutterActivity.withNewEngine()
                        .url(Objects.requireNonNull(pageName.get(path)))
                        .params(params)
                        .backgroundMode(BoostFlutterActivity.BackgroundMode.opaque)
                        .build(context);
                if (context instanceof Activity ) {

                    Log.i("openPageByUrl", "activity.startActivityForResult(intent, requestCode) " + requestCode);

                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent, requestCode);
                } else {
                    context.startActivity(intent);
                }
                return true;
            } else if (url.startsWith(FLUTTER_FRAGMENT_PAGE_URL)) {
                //context.startActivity(new Intent(context, FlutterFragmentPageActivity.class));
                return true;
            } else if (url.startsWith(NATIVE_PAGE_URL)) {

                // url 可参考阿里巴巴 ARouter 设计
                Intent intent = new Intent(context, TestActivity.class);
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    activity.startActivityForResult(intent, requestCode);
                } else {
                    context.startActivity(intent);
                }

                return true;
            }

            return false;

        } catch (Throwable t) {

            // H5 打开页面容错

            return false;
        }
    }
}
