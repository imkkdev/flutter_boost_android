package com.flutter.app.hybrid

import android.app.Application
import com.flutter.app.hybrid.flutter.FlutterBoostManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FlutterBoostManager.initFlutterBoost(this)
    }

}