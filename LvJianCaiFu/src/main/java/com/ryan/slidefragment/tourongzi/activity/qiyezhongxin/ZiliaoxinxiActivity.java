package com.ryan.slidefragment.tourongzi.activity.qiyezhongxin;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.slidefragment.generaldemo.R;

public class ZiliaoxinxiActivity extends Activity {

    private TextView title,sick_title_right_tv;
    private LinearLayout sick_titel_left_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ziliaoxinxi);
        initView();
    }

    private void initView() {
        title= (TextView) findViewById(R.id.sick_title_mid_tv);
        sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
        sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
        title.setText("资料信息");
        sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
        sick_titel_left_layout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });
    }
}
