package com.ryan.slidefragment.tourongzi.activity.qiyezhongxin;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ryan.slidefragment.generaldemo.R;

public class FachutoudiActivity extends Activity implements View.OnClickListener {

    private TextView sick_title_mid_tv, sick_title_right_tv,sick_title_left_tv;
    private ImageView sick_title_left_img;
    private PullToRefreshListView pullToRefresh;
    /**
     * listview的item布局是activity_fachutoudi
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.activity_cheng_gong_an_li);
        sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
        sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
        sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
        sick_title_left_img.setOnClickListener(this);
        sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
        sick_title_left_tv.setOnClickListener(this);
        sick_title_mid_tv.setText("发出的投递");
        sick_title_right_tv.setVisibility(View.GONE);
        pullToRefresh = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
    }

    @Override
    public void onClick(View view) {

    }
}
