package com.flutter.app.hybrid.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flutter.app.hybrid.PageRouter
import com.flutter.app.hybrid.R
import com.flutter.app.hybrid.util.StartFlutterProxy
import com.idlefish.flutterboost.interfaces.IFlutterViewContainer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            // 跳转Flutter，等待返回值
            PageRouter.openPageByUrl(
                this,
                "/first",
                null,
                100
            )
        }

        button3.setOnClickListener {
            // 原生方式跳转Flutter
            StartFlutterProxy.startActivity(this@MainActivity, "/second")
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            // 使用 IFlutterViewContainer.RESULT_KEY 获取数据
            println("resultCode=$resultCode, data=${data?.extras?.get(IFlutterViewContainer.RESULT_KEY)}")
        }
    }
}
