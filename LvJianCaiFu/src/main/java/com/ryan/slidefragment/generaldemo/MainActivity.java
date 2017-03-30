package com.ryan.slidefragment.generaldemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.ryan.slidefragment.base.BaseApplication;
import com.ryan.slidefragment.domain.UserInfoBean;
import com.ryan.slidefragment.fragment.ChanPinFragment;
import com.ryan.slidefragment.fragment.HomeFragment;
import com.ryan.slidefragment.fragment.MemberFragment;
import com.ryan.slidefragment.fragment.MineFragment;
import com.ryan.slidefragment.fragment.PolicyFragment;
import com.ryan.slidefragment.tools.SlidingMenuTool;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener{
	private static final String TAG = "MainActivity";
	private SparseArray<Fragment> navigateMap = new SparseArray<Fragment>();

	@Override  
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		setContentView(R.layout.activity_main);

		////////动态注册广播  
		IntentFilter mFilter = new IntentFilter();  
		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);  
		registerReceiver(myNetReceiver, mFilter);  
//		/////////发送广播附加信息
//		Intent intent=new Intent();  
//		//intent.setAction(_ACTION);  
//		intent.putExtra("msg", "hello");  
//		this.sendBroadcast(intent);

		FragmentManager fm = getSupportFragmentManager();
		//添加侧滑菜单
		SlidingMenuTool.slidingMenuView(fm, this, this);
		UserInfoBean userInfo=new UserInfoBean();
		String userID = userInfo.getUserID();
		System.out.println("用户id="+userID);
		
		//添加选项卡
		intiFragment(fm);
	}

	/**注册广播监听网络变化*/
	private ConnectivityManager mConnectivityManager;  

	private NetworkInfo netInfo;  

	/////////////监听网络状态变化的广播接收器  

	private BroadcastReceiver myNetReceiver = new BroadcastReceiver() {  

		@Override  
		public void onReceive(Context context, Intent intent) {  

			String action = intent.getAction();  
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {  

				mConnectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);  
				netInfo = mConnectivityManager.getActiveNetworkInfo();    
				if(netInfo != null && netInfo.isAvailable()) {  

					/////////////网络连接  
					String name = netInfo.getTypeName();  

					if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){  
						/////WiFi网络  
						Log.i("网路监听", "wifi");
						Toast.makeText(MainActivity.this, "你正在使用wifi", Toast.LENGTH_LONG).show();
					}else if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){  
						/////有线网络  
						Log.i("网路监听", "有线网络");
					}else if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){  
						/////////3g网络  
						Log.i("网路监听", "3G网络");
						Toast.makeText(MainActivity.this, "你正在使用3G", Toast.LENGTH_LONG).show();
					}  
				} else {  
					////////网络断开  
					Log.i("网络监听", "没有网络");
					Toast.makeText(MainActivity.this, "没有网络,请检查网络连接", Toast.LENGTH_LONG).show();
				}  
			}  
//	////////接收广播时判断附加信息
//			String msg=intent.getStringExtra("msg");
//			if(msg.equals("hello")){
//				Log.i("接收广播时判断附加信息=", msg);
//			}

		}   
	};   



	/**
	 * 初始换选项卡
	 */
	private void intiFragment(FragmentManager fm){

		navigateMap.clear();
		mapNaviToFragment(R.id.home_tag_id, new HomeFragment());//首页
		//		navigateMap.clear();
		mapNaviToFragment(R.id.message_tag_id, new MemberFragment()); //服务
		//		navigateMap.clear();
		mapNaviToFragment(R.id.search_tag_id, new PolicyFragment()); //成员
		//		navigateMap.clear();
		//购物车
		mapNaviToFragment(R.id.aaa_tag_id,new ChanPinFragment());


		mapNaviToFragment(R.id.myinfo_tag_id, new MineFragment()); //我的模块

		//设置首页默认显示
		hideorshow(fm, R.id.home_tag_id);
	}

	/**
	 * 初始化map
	 * @param id 导航view ID
	 * @param fragment
	 */
	private void mapNaviToFragment(int id, Fragment fragment) {
		//		name = (TextView) findViewById(R.id.name);
		//		name.setText(str);
		View view = findViewById(id);
		view.setOnClickListener(this);
		view.setSelected(false);
		navigateMap.put(id, fragment);
		//		navigate.put(id,str);
	}

	/*
	 * 显示和隐藏fragment
	 */
	private void hideorshow(FragmentManager fm, int id){
		Log.i(TAG, "replaceFragment EntryCount: " + fm.getBackStackEntryCount()
				+ " size: " );
		String tag = String.valueOf(id);
		FragmentTransaction trans = fm.beginTransaction();
		if(null == fm.findFragmentByTag(tag)) {
			trans.replace(R.id.contentframe, navigateMap.get(id), tag);
		}else{
			trans.show(navigateMap.get(id));
		}
		trans.commit();
		//重置导航选中状态
		for(int i=0, size=navigateMap.size(); i<size; i++) {
			int curId = navigateMap.keyAt(i);
			Log.i(TAG, "curId: " + curId);
			if(curId == id) {
				findViewById(id).setSelected(true);
			} else {
				findViewById(curId).setSelected(false);
			}
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	public void onClick(View view) {
		// TODO Auto-generated method stub
		int id = view.getId();
		if(navigateMap.indexOfKey(id) >= 0) {

			if(!view.isSelected()) {
				//当前非选中状态：需切换到新内容
				hideorshow(getSupportFragmentManager(), id);
			} else {
				Log.i(TAG, " ignore --- selected !!! ");
			}
		}
	}
	private long mExitTime;
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {

				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
				/////////解除广播  

				if(myNetReceiver!=null){  
					unregisterReceiver(myNetReceiver);  
				}  
			}
			return true;
		} 
		return super.onKeyDown(keyCode, event);
	}
	//切换按钮
	public void showLeftMenu(View view)
	{
		getSlidingMenu().showMenu();
	}
}
