package com.flutter.app.hybrid.flutter

import android.app.Application
import android.util.Log
import com.flutter.app.hybrid.PageRouter
import com.flutter.app.hybrid.plugin.BatteryPlugin
import com.flutter.app.hybrid.plugin.TestPlugin
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.Utils
import com.idlefish.flutterboost.interfaces.INativeRouter
import io.flutter.embedding.android.FlutterView

object FlutterBoostManager {

    @JvmStatic
    fun initFlutterBoost(app: Application) {

        // Flutter 跳转监听, Flutter->Flutter, Flutter->Native
        val router = INativeRouter { context, url, urlParams, requestCode, exts ->

            Log.d(
                "INativeRouter",
                "url is $url , param is $urlParams,  requestCode is $requestCode"
            )

            val assembleUrl = Utils.assembleUrl(url, urlParams)
            PageRouter.openPageByUrl(context, assembleUrl, urlParams, requestCode)
        }

        val boostLifecycleListener: FlutterBoost.BoostLifecycleListener = object :
            FlutterBoost.BoostLifecycleListener {
            override fun beforeCreateEngine() {}
            override fun onEngineCreated() {

                Log.d("FlutterBoost", "onEngineCreated: ")

                // 方法一：Flutter官方注册插件方式
                BatteryPlugin.register(FlutterBoost.instance().engineProvider().dartExecutor)

                // 方法二：FlutterBoost 文档方式注册插件
                FlutterBoost.instance().engineProvider().plugins.add(TestPlugin())
            }

            override fun onPluginsRegistered() {
            }
            override fun onEngineDestroy() {}
        }

        //
        // AndroidManifest.xml 中必须要添加 flutterEmbedding 版本设置
        //
        //   <meta-data android:name="flutterEmbedding"
        //               android:value="2">
        //    </meta-data>
        // GeneratedPluginRegistrant 会自动生成 新的插件方式　
        //
        // 插件注册方式请使用
        // FlutterBoost.instance().engineProvider().getPlugins().add(new FlutterPlugin());
        // GeneratedPluginRegistrant.registerWith()，是在engine 创建后马上执行，放射形式调用
        //
        val platform = FlutterBoost.ConfigBuilder(app, router)
            .isDebug(true)
            .whenEngineStart(FlutterBoost.ConfigBuilder.ANY_ACTIVITY_CREATED)
            .renderMode(FlutterView.RenderMode.texture)
            .lifecycleListener(boostLifecycleListener)
            .build()
        FlutterBoost.instance().init(platform)



    }

}