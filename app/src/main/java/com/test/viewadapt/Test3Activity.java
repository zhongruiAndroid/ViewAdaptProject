package com.test.viewadapt;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.zr.FrameLayoutAdapt;
import com.zr.TextViewAdapt;

public class Test3Activity extends AppCompatActivity {

    private FrameLayoutAdapt fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        fl = findViewById(R.id.fl);

        View btAddView = findViewById(R.id.btAddView);
        btAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl.removeAllViews();
                TextViewAdapt textViewAdapt = new TextViewAdapt(Test3Activity.this);
                textViewAdapt.setId(R.id.tvTest);
                FrameLayoutAdapt.LayoutParams layoutParams = new FrameLayoutAdapt.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.getLayoutAdaptInfo().widthAdapt=500;
                layoutParams.getLayoutAdaptInfo().heightAdapt=500;
        //      layoutParams.getLayoutAdaptInfo().widthAdapt=1;
        //      layoutParams.getLayoutAdaptInfo().heightAdapt=1;
        //      layoutParams.getLayoutAdaptInfo().leftMarginAdapt=1;
        //      layoutParams.getLayoutAdaptInfo().topMarginAdapt=1;
        //      layoutParams.getLayoutAdaptInfo().rightMarginAdapt=1;
        //      layoutParams.getLayoutAdaptInfo().bottomMarginAdapt=1;
        //      layoutParams.getLayoutAdaptInfo().startMarginAdapt=1;
        //      layoutParams.getLayoutAdaptInfo().endMarginAdapt=1;

                textViewAdapt.setText("test");
                textViewAdapt.setBackgroundColor(Color.BLUE);
                textViewAdapt.setLayoutParams(layoutParams);

                fl.addView(textViewAdapt);

            }
        });

        View btSetView = findViewById(R.id.btSetView);
        btSetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findViewById(R.id.tvTest)==null){
                    return;
                }
                TextViewAdapt tvTest = findViewById(R.id.tvTest);
                FrameLayoutAdapt.LayoutParams layoutParams = (FrameLayoutAdapt.LayoutParams) tvTest.getLayoutParams();
                layoutParams.getLayoutAdaptInfo().widthAdapt=300;
                layoutParams.getLayoutAdaptInfo().heightAdapt=300;
                tvTest.setLayoutParams(layoutParams);


                tvTest.setPaddingAdapt(10,10,10,10);
            }
        });
    }
}
