package com.ryan.slidefragment.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

public abstract class BaseFragment extends Fragment {

	public Activity mActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = initView();
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		FragmentManager fragmentManager = getFragmentManager();
		initData(fragmentManager);
	}


	/**
	 * 子类通过这个方法控制dialog
	 */
	public void setDialog() {
		
	}
	public abstract View initView();

	public void initData(FragmentManager fragmentManager) {
		initData();
	}

	public void initData() {
		
	}
	public void hideSoftKeyboard(){
		InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);            
		 if(imm.isActive()&&getActivity().getCurrentFocus()!=null){
		    if (getActivity().getCurrentFocus().getWindowToken()!=null) {
		    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		    }             
		 }
	}
}

