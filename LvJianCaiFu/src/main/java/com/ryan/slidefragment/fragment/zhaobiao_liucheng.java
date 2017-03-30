package com.ryan.slidefragment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ryan.slidefragment.generaldemo.R;

public class zhaobiao_liucheng extends Fragment{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	LinearLayout i = (LinearLayout) inflater.inflate(R.layout.zhaobiao_liucheng,
			container, false);
	return i;
}
}
