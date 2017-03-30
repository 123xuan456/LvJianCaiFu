package com.ryan.slidefragment.generaldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryan.slidefragment.Data.Data;
import com.ryan.slidefragment.base.BaseFragment;
import com.ryan.slidefragment.fragment.AllBaby_F;

 
/**
 * 假数据，购物车
 */

public class GouWuCheFragment extends BaseFragment implements OnClickListener {
	private TextView bt_cart_edit;
	private AllBaby_F allBaby_F;
	private boolean isDel=true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.shopping_car_activity, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		((TextView) view.findViewById(R.id.tv_top_txtTitle)).setText("我的购物车");
		bt_cart_edit = (TextView) view.findViewById(R.id.tv_top_edit);
		bt_cart_edit.setOnClickListener(this);

		allBaby_F = new AllBaby_F();
		addFragment(allBaby_F);
		showFragment(allBaby_F);

	}

	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_top_edit:
			if (allBaby_F!=null&&isDel) {
				removeFragment(allBaby_F);
				allBaby_F=null;
				allBaby_F=new AllBaby_F("删除");
				addFragment(allBaby_F);
				showFragment(allBaby_F);
				isDel=false;
				bt_cart_edit.setText("完成");
				Data.Allprice_cart=0;
				
			}else if (!isDel&&allBaby_F!=null) {
				removeFragment(allBaby_F);
				allBaby_F=null;
				allBaby_F=new AllBaby_F();
				addFragment(allBaby_F);
				showFragment(allBaby_F);
				isDel=true;
				Data.Allprice_cart=0;
				bt_cart_edit.setText("编辑");
			}
			break;
		default:
			break;
		}
	}

	/** 添加Fragment **/
	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		ft.add(R.id.show_cart_view, fragment);
		ft.commitAllowingStateLoss();
	}
	/** 删除Fragment **/
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		ft.remove(fragment);
		ft.commitAllowingStateLoss();
	}

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		if (allBaby_F != null) {
			ft.hide(allBaby_F);
		}
		ft.show(fragment);
		ft.commitAllowingStateLoss();

	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		return null;
	}

}
	