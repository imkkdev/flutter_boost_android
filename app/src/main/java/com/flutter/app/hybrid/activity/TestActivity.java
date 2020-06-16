package com.flutter.app.hybrid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.flutter.app.hybrid.R;
import com.idlefish.flutterboost.interfaces.IFlutterViewContainer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.icon).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // 返回数据给Flutter
        Map<String, Object> map = new HashMap<>();
        map.put("params_name", "赵六");
        map.put("params_age", 48);
        Intent intent = new Intent();
        intent.putExtra(IFlutterViewContainer.RESULT_KEY, (Serializable) map);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
