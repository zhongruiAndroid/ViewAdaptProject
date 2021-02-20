package com.test.viewadapt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test2);
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
}
