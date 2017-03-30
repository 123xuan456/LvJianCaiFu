package com.ryan.slidefragment.tourongzi.activity.jurenmai;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.loader.ImageCache;
import com.ryan.slidefragment.loader.ImageFetcher;
import com.ryan.slidefragment.loader.ImageWorker;
import com.ryan.slidefragment.options.Constants;
import com.ryan.slidefragment.tourongzi.ben.JuRenMai_XiangQing_ben;
import com.ryan.slidefragment.utils.JsonUtils;
import com.volley.JsonJudger;

public class JuRenMaiXiangQing_Activity extends Activity {
    private TextView title,sick_title_right_tv;
    private LinearLayout sick_titel_left_layout;
    private JuRenMai_XiangQing_ben jrmxq;
    private TextView xingbie;
    private TextView textView11,textView15,textView17;
    private ImageView imageView8;
    private ImageWorker mImageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ju_ren_mai_xiang_qing_);
        setview();
        mImageLoader = new ImageFetcher(this);
        mImageLoader.setImageCache(ImageCache.getInstance(this));

        Intent i=getIntent();
        String id = i.getStringExtra("id");
        getdata(id);
    }

    private void getdata(String id1) {
        String url= Constants.TOURONGZI_JURENMAI_XIANGQING+id1;
        HttpUtils http=new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET,
                url,
                new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result.toString();
                        System.out.println("参数成功?" + result);
                        JSonData(result);

                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        System.out.println("传递失败");
                    }
                });

    }

    private void JSonData(String result) {
        jrmxq= JsonUtils.parser(result, JuRenMai_XiangQing_ben.class);
        xingbie.setText(jrmxq.getXing()+jrmxq.getBie());
        textView11.setText(jrmxq.getZhiwei());
        textView15.setText(jrmxq.getSite());
        textView17.setText(jrmxq.getSite());
        mImageLoader.loadImage(jrmxq.getImage(),
                imageView8, R.drawable.rongzi);
    }

    private void setview() {
        title= (TextView) findViewById(R.id.sick_title_mid_tv);
        sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
        sick_title_right_tv.setVisibility(View.GONE);//隐藏字体
        title.setText("聚人脉详情");
        sick_titel_left_layout=(LinearLayout) findViewById(R.id.sick_titel_left_layout);
        sick_titel_left_layout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });

        xingbie=(TextView)findViewById(R.id.xingbie);
        textView11=(TextView)findViewById(R.id.textView11);//职位
        textView15=(TextView)findViewById(R.id.textView15);//地区
        textView17=(TextView)findViewById(R.id.textView17);//行业
        imageView8=(ImageView)findViewById(R.id.imageView8);//图片



    }


}
