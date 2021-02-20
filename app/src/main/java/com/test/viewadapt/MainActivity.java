package com.test.viewadapt;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    private View btTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btTest = findViewById(R.id.btTest);
        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestActivity.class);
            }
        });
        View btTest2 = findViewById(R.id.btTest2);
        btTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Test2Activity.class);
            }
        });

        final CheckBox cbAdaptCutout = findViewById(R.id.cbAdaptCutout);
        final CheckBox cbFullScreen = findViewById(R.id.cbFullScreen);
        cbFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenUtils.fullScreen(getWindow(), cbFullScreen.isChecked(),cbAdaptCutout.isChecked());

            }
        });
        final CheckBox cbHiddenNavigation = findViewById(R.id.cbHiddenNavigation);
        cbHiddenNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbHiddenNavigation.isChecked()) {
                    FullScreenUtils.hideNavigationBar(getWindow());
                } else {
                    FullScreenUtils.showNavigationBar(getWindow());
                }
            }
        });

    }
    public void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("=====", "=====onConfigurationChanged" + newConfig.orientation);
    }
}