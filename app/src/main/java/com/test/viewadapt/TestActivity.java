package com.test.viewadapt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class TestActivity extends AppCompatActivity {

    private View fl;
    private View tv1;
    private View tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        fl = findViewById(R.id.fl);
        tv1 = findViewById(R.id.tv1);
//        tv2 = findViewById(R.id.tv2);
        fl.post(new Runnable() {
            @Override
            public void run() {
//                LG.log("tv2:"+tv2.getWidth()+"=="+tv2.getMeasuredWidth());
            }
        });
    }
}
