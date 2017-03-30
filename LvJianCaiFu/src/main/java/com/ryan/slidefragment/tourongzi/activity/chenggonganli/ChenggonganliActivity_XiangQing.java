package com.ryan.slidefragment.tourongzi.activity.chenggonganli;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.ben.ChengGong_ben;
import com.ryan.slidefragment.utils.JsonUtils;
import com.volley.CacheUtils;

import java.util.ArrayList;
import java.util.Map;

public class ChenggonganliActivity_XiangQing extends Activity {
    private TextView title,yongtu,mingzi,fangshi,zijin,provinceid,hangye,titil,time,cont;
    private TextView sick_title_mid_tv, sick_title_right_tv,
            sick_title_left_tv;
    private ImageView sick_title_left_img;
    private ImageView iv_baby_collection_1,iv_baby_collection;
    private ArrayList<Map<String, Object>> list;
    private ChengGong_ben cg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chenggonganli_activity__xiang_qing);
        Intent i=getIntent();
        String ids = i.getStringExtra("id");
        setView();
        getData(ids);
    }

    private void setView() {
        sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
        sick_title_left_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
        sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
        sick_title_mid_tv.setText("详情");
        iv_baby_collection_1= (ImageView) findViewById(R.id.iv_baby_collection_1);
        iv_baby_collection= (ImageView) findViewById(R.id.iv_baby_collection);

        cont= (TextView) findViewById(R.id.cont);
        title= (TextView) findViewById(R.id.title);
        yongtu= (TextView) findViewById(R.id.yongtu);
        mingzi= (TextView) findViewById(R.id.mingzi);
        fangshi= (TextView) findViewById(R.id.fangshi);
        zijin= (TextView) findViewById(R.id.zijin);
        provinceid= (TextView) findViewById(R.id.provinceid);
        hangye= (TextView) findViewById(R.id.hangye);
        titil= (TextView) findViewById(R.id.titil);
        time= (TextView) findViewById(R.id.time);
    }


    public void getData(String ids) {
        String url= Constants.TOURONGZIDIALOG_CHENGGONGXIANGXI+ids;
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result.toString();
                System.out.println("result="+s);
                parse(s);
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });

    }

    private void parse(String s) {
        cg= JsonUtils.parser(s,
                ChengGong_ben.class);
        CacheUtils.putString("rows", cg.getContent());
        String  x=cg.getMingzi()+cg.getSex();
        mingzi.setText(x);
        title.setText(cg.getTitle());
        yongtu.setText(cg.getYongtu());
        fangshi.setText(cg.getFangshi());
        provinceid.setText(cg.getProvince());
        hangye.setText(cg.getHangye());
        cont.setText(cg.getContent());
        zijin.setText(cg.getZijin());
        titil.setText(cg.getTitle());
    }
}
