package com.ryan.slidefragment.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.slidefragment.Model;
import com.ryan.slidefragment.adapter.GridViewAdapter;
import com.ryan.slidefragment.domain.Type;
import com.ryan.slidefragment.generaldemo.ListViewChanPin;
import com.ryan.slidefragment.generaldemo.R;



public class ProTypeFragment extends Fragment {
	private ArrayList<Type> list;
	private GridView gridView;
	private GridViewAdapter adapter;   
	private Type type;
	private String typename;
	private int icon;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pro_type, null);
		gridView = (GridView) view.findViewById(R.id.listView);
		int index = getArguments().getInt("index");

		typename = Model.toolsList[index];
		icon = Model.iconList[index];

		((TextView) view.findViewById(R.id.toptype)).setText(typename);
		GetTypeList();
		adapter = new GridViewAdapter(getActivity(), list);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position) {
				case 0:
					Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT)
					.show();
					break;
				case 1:
					Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT)
					.show();
					break;
				case 2:
					Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT)
					.show();
					break;
				case 3:
					Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT)
					.show();
					break;
				case 4:
						Intent intent = new Intent(getActivity(),ListViewChanPin.class);
						startActivity(intent);
					break;
				case 5:
					Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT)
					.show();
					break;
				case 6:
					Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT)
					.show();
					break;
				case 7:
					Toast.makeText(getActivity(), "暂未开放", Toast.LENGTH_SHORT)
					.show();
					break;
					

				default:
					break;
				}
				
			}
		});

		return view;
	}

	private void GetTypeList() {
		list = new ArrayList<Type>();
		for (int i = 1; i < 9; i++) {
			type = new Type(i, typename + i, icon);
			list.add(type);
		}
	}
}

