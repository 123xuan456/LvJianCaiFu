//package com.ryan.slidefragment.adapter;
//
//import java.util.ArrayList;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//
//import com.bjzy.qctt.base.BaseActivity;
//import com.bjzy.qctt.base.BaseApplication;
//import com.bjzy.qctt.base.BaseItemView;
//import com.bjzy.qctt.dao.JumpTypeDao;
//import com.bjzy.qctt.domain.NewsListBean;
//import com.bjzy.qctt.domain.NewsListBean.Data.NewsList;
//import com.bjzy.qctt.factory.ViewFactory;
//import com.bjzy.qctt.options.Constants;
//import com.bjzy.qctt.options.QcttGlobal;
//import com.taoche.qctt.R;
//
//public class InfomationContentAdapter extends BaseAdapter{
//private Context context;
//public  ArrayList<NewsList> newsList;
//private NewsList news;
//private int  itemNum;
//private Boolean ISADDHEAD = false;
//	public InfomationContentAdapter(Context context, NewsListBean newsListBean) {
//		this.context = context;
//		newsList = newsListBean.data.newsList;
//	}
//	public void isAddHead(){
//		 ISADDHEAD = true;
//	}
//	/**
//	 * 设置列表用于下拉刷新
//	 * @param page
//	 * @param newsListBean
//	 */
//	public void setList(int page,ArrayList<NewsList> newsList){
//		this.newsList = newsList;
//	}
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return newsList.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//	
//			this.itemNum = position;
//		
//		news = newsList.get(position);
//		 //style 0,无图，1单张小图，2,单张大图3多图 4.大图推广 5.小图推广 6.专题(统一小图)
//			int viewType = Integer.valueOf(news.style).intValue();
//	
//			View views = ViewFactory.CreatView(viewType, context);
//			BaseItemView view = (BaseItemView) views.findViewById(R.id.onepic_special);
//		
//		//0,普通,1,热门2,原创,3,推广,4,专题,5推荐6.头条
//		int styleType  = Integer.valueOf(news.articleType).intValue();
//		
//		view.setiv_image(news.picUrlList);
//		view.setinfomation_content(news.excerpt);
//		view.setinfomation_item_time(news.publishTime);
//		view.setinfomation_title(news.title);
//		view.setinfomation_item_reader(news.readCount);
//		
//		if(isRead(news.resourceLoc)){
//			view.setTitleColor(Color.GRAY);
//		}else{
//			view.setTitleColor(Color.BLACK);
//		}
//		
//		if (styleType == 3) {
//		view.setinfomation_item_push(styleType, viewType);
//		}
//		view.setiv_labe(styleType);
//		
//		
//		return view;
//	}
//	
//	/**
//	 * 判断首页列表中的项是否已读
//	 * @param resourceLoc 列表项ID
//	 * @return
//	 */
//	private boolean isRead(String resourceLoc){
//		boolean isRead = false;
//		String ids = QcttGlobal.read(context,Constants.QCTT_SETTINS,"READ_LIST_ID");
//		String infos[]=ids.split(",");
//		for(String eachInfo:infos){
//			if(eachInfo.equals(resourceLoc)){
//				isRead = true;
//				break;
//			}
//		}
//		return isRead;
//	}
//}
