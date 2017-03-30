package com.ryan.slidefragment.generaldemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JiaRuLianMengActivity extends Activity implements OnClickListener {
	private TextView sick_title_mid_tv, sick_title_right_tv,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
	private ImageView sick_title_left_img, img_tianjiatupian;
	private Button b_tijiao;
	Uri uri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_jia_ru_lian_meng);
		sick_title_left_img = (ImageView) findViewById(R.id.sick_title_left_img);
		sick_title_mid_tv = (TextView) findViewById(R.id.sick_title_mid_tv);
		sick_title_right_tv = (TextView) findViewById(R.id.sick_title_right_tv);
		sick_title_left_img.setOnClickListener(this);
		sick_title_mid_tv.setText("加入联盟");
		sick_title_right_tv.setVisibility(View.GONE);
		img_tianjiatupian = (ImageView) findViewById(R.id.img_tianjiatupian);
		img_tianjiatupian.setOnClickListener(this);

		t1 = (TextView) findViewById(R.id.t1);
		t2 = (TextView) findViewById(R.id.t2);
		t3 = (TextView) findViewById(R.id.t3);
		t4 = (TextView) findViewById(R.id.t4);
		t5 = (TextView) findViewById(R.id.t5);
		t6 = (TextView) findViewById(R.id.t6);
		t7 = (TextView) findViewById(R.id.t7);
		t8 = (TextView) findViewById(R.id.t8);
		t9 = (TextView) findViewById(R.id.t9);
		t10 = (TextView) findViewById(R.id.t10);
		t11 = (TextView) findViewById(R.id.t11);
		t12 = (TextView) findViewById(R.id.t12);
		t13 = (TextView) findViewById(R.id.t13);
		
		b_tijiao = (Button) findViewById(R.id.b_tijiao);
		b_tijiao.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		// 返回键
		case R.id.sick_title_left_img:
			finish();
			break;
		// 提交
		case R.id.b_tijiao:
			String tt1 = t1.getText().toString();
			if(tt1.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"企业名称不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt2 = t2.getText().toString();
			if(tt2.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"注册地址不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt3 = t3.getText().toString();
			if(tt3.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"法人代表不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt4 = t4.getText().toString();
			if(tt4.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"注册资金不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt5 = t5.getText().toString();
			if(tt5.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"公司类型不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt6 = t6.getText().toString();
			if(tt6.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"税务登记证号不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt7 = t7.getText().toString();
			if(tt7.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"组织机构代码证号不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt8 = t8.getText().toString();
			if(tt8.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"贷款卡号码不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt9 = t9.getText().toString();
			if(tt9.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"联系地址不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt10 = t10.getText().toString();
			if(tt10.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"企业邮箱不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt11 = t11.getText().toString();
			if(tt11.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"电话、传真不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt12 = t12.getText().toString();
			if(tt12.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"开户行不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			String tt13 = t13.getText().toString();
			if(tt13.length() == 0){
			    Toast.makeText(JiaRuLianMengActivity.this,"开户行账号不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			if(uri==null){
			    Toast.makeText(JiaRuLianMengActivity.this,"营业执照不能为空",Toast.LENGTH_SHORT).show();    //弹出一个自动消失的提示框
			    return;
			}
			Intent i = new Intent(JiaRuLianMengActivity.this,
					ShenQingActivity.class);
			startActivity(i);
			break;
		// 拍照
		case R.id.img_tianjiatupian:

			// 跳转至拍照界面
			Intent intentPhote = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intentPhote.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			File out = new File(getPhotopath());
			uri = Uri.fromFile(out);
			// 获取拍照后未压缩的原图片，并保存在uri路径中
			intentPhote.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			startActivityForResult(intentPhote, 2000);
			break;

		default:
			break;
		}
	}

	/**
	 * 获取原图片存储路径
	 * 
	 * @return
	 */
	private String getPhotopath() {
		// 照片全路径
		String fileName = "";
		// 文件夹路径
		String pathUrl = Environment.getExternalStorageDirectory() + "/mymy/";
		String imageName = "imageTest.jpg";
		File file = new File(pathUrl);
		file.mkdirs();// 创建文件夹
		fileName = pathUrl + imageName;
		return fileName;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2000 && resultCode == Activity.RESULT_OK) {
			Bitmap bitmap = getBitmapFromUrl(getPhotopath(), 313.5, 462.0);
			saveScalePhoto(bitmap);
			img_tianjiatupian.setImageBitmap(bitmap);
		}
	}

	/**
	 * 根据路径获取图片资源（已缩放）
	 * 
	 * @param url
	 *            图片存储路径
	 * @param width
	 *            缩放的宽度
	 * @param height
	 *            缩放的高度
	 * @return
	 */
	private Bitmap getBitmapFromUrl(String url, double width, double height) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false
		Bitmap bitmap = BitmapFactory.decodeFile(url);
		// 防止OOM发生
		options.inJustDecodeBounds = false;
		int mWidth = bitmap.getWidth();
		int mHeight = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = 1;
		float scaleHeight = 1;
		// try {
		// ExifInterface exif = new ExifInterface(url);
		// String model = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// 按照固定宽高进行缩放
		// 这里希望知道照片是横屏拍摄还是竖屏拍摄
		// 因为两种方式宽高不同，缩放效果就会不同
		// 这里用了比较笨的方式
		if (mWidth <= mHeight) {
			scaleWidth = (float) (width / mWidth);
			scaleHeight = (float) (height / mHeight);
		} else {
			scaleWidth = (float) (height / mWidth);
			scaleHeight = (float) (width / mHeight);
		}
		// matrix.postRotate(90); /* 翻转90度 */
		// 按照固定大小对图片进行缩放
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight,
				matrix, true);
		// 用完了记得回收
		bitmap.recycle();
		return newBitmap;
	}

	/**
	 * 存储缩放的图片
	 * 
	 *            图片数据
	 */
	private void saveScalePhoto(Bitmap bitmap) {
		// 照片全路径
		String fileName = "";
		// 文件夹路径
		String pathUrl = Environment.getExternalStorageDirectory().getPath()
				+ "/mymy/";
		String imageName = "imageScale.jpg";
		FileOutputStream fos = null;
		File file = new File(pathUrl);
		file.mkdirs();// 创建文件夹
		fileName = pathUrl + imageName;
		try {
			fos = new FileOutputStream(fileName);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
