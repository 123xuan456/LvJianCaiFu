package com.ryan.slidefragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.generaldemo.DifangzhengceActivity;
import com.ryan.slidefragment.generaldemo.HangyezhengceActivity;
import com.ryan.slidefragment.generaldemo.LianmengzhengceActivity;
import com.ryan.slidefragment.generaldemo.R;
import com.ryan.slidefragment.generaldemo.ZhengCiActivity;
import com.ryan.slidefragment.view.CircleImageView;
import com.ryan.slidefragment.view.CircleLayout;
import com.ryan.slidefragment.view.CircleLayout.OnItemClickListener;
import com.ryan.slidefragment.view.CircleLayout.OnItemSelectedListener;

public class TechnologyFragment extends FragmentActivity implements
		OnItemSelectedListener, OnItemClickListener, OnClickListener {
	TextView selectedTextView;
	private TextView sick_title_right_tv, sick_title_mid_tv,sick_title_left_tv;
	private ImageView sick_title_left_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_myinfo);

		// sick_title_right_tv = (TextView)
		// findViewById(R.id.sick_title_right_tv);
		// sick_title_right_tv.setVisibility(View.GONE);
		// sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		// sick_title_mid_tv.setText("政策汇编");
		// sick_title_left_img = (ImageView)
		// findViewById(R.id.sick_title_left_img);
		// sick_title_left_img.setOnClickListener(this);
		CircleImageView main_facebook_image = (CircleImageView) findViewById(R.id.main_facebook_image);
		CircleImageView main_myspace_image = (CircleImageView) findViewById(R.id.main_myspace_image);
		CircleImageView main_google_image = (CircleImageView) findViewById(R.id.main_google_image);
		CircleImageView main_linkedin_image = (CircleImageView) findViewById(R.id.main_linkedin_image);
		CircleLayout circleMenu = (CircleLayout) findViewById(R.id.main_circle_layout);
		sick_title_left_tv = (TextView) findViewById(R.id.sick_title_left_tv);
		sick_title_left_tv.setOnClickListener(this);
		
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_mid_tv.setText("政策信息");
		sick_title_mid_tv.setTextColor(Color.WHITE);
		sick_title_mid_tv.setTextSize(20);
		
		sick_title_right_tv=(TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_right_tv.setVisibility(View.GONE);
		
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_left_img.setOnClickListener(this);
		
		
		circleMenu.setOnItemSelectedListener(this);
		circleMenu.setOnItemClickListener(this);
		selectedTextView = (TextView) findViewById(R.id.main_selected_textView);
		selectedTextView.setText(((CircleImageView) circleMenu
				.getSelectedItem()).getName());
		main_facebook_image.setOnClickListener(this);
		main_myspace_image.setOnClickListener(this);
		main_google_image.setOnClickListener(this);
		main_linkedin_image.setOnClickListener(this);

	}

	public void onItemClick(View view, int position, long id, String name) {
		Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT)
				.show();

	}

	public void onItemSelected(View view, int position, long id, String name) {

		selectedTextView.setText(name);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		 case R.id.sick_title_left_img:
		finish();
		break;
		 case R.id.sick_title_left_tv:
			 finish();
			 break;
		case R.id.main_facebook_image:
			//国家政策
			Intent i = new Intent(TechnologyFragment.this,
					ZhengCiActivity.class);
			startActivity(i);
			break;
		case R.id.main_myspace_image:
			//地方政策
			Intent i1 = new Intent(TechnologyFragment.this,
					DifangzhengceActivity.class);
			startActivity(i1);
			break;
		case R.id.main_google_image:
			//联盟政策
			Intent i2 = new Intent(TechnologyFragment.this,
					LianmengzhengceActivity.class);
			startActivity(i2);
			break;
		case R.id.main_linkedin_image:
			//行业政策
			Intent i3 = new Intent(TechnologyFragment.this,
					HangyezhengceActivity.class);
			startActivity(i3);
			break;

		default:
			break;
		}

	}

}
