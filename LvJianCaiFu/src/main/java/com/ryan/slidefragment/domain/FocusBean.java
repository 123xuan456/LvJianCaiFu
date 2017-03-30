package com.ryan.slidefragment.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 首页模块里顶部的轮播图
 * @author yongzhen
 *
 */

public class FocusBean implements Serializable{
	/**
	 * 实现数据持久化
	 */
	private static final long serialVersionUID = 1L;
	public String code;
	public Date date;
	public class Date{
		public ArrayList<Content> content;
		public class Content{
			public String alt;
			public int bid;
			public String creattime;
			public int id;
			public String image;
			public String imgurl;
			public String name;
			public String playtime;
			public String productId;
			public int state;
			public String title;
			
			public Droduct product;
			public class Droduct{
				public String address;
				public String authentication;
				public String bank;
				public String birthday;
				public String creattime;
				public String description;
				public int dianjicishu;
				public int id;
				public String jiagedanwei;
				public int measure;
				public int minimum;
				public String model;
				public String name;
				public String norms;
				public int price;
				public String ptype;
				public int quanxian;
				public String revolve;
				public int shoufei;
				public int state;
				public int statu;
				public int tid;
				public int usersId;
				public int zongliang;
				public ArrayList<Imagesssss> Imagesssss;
				public class Imagesssss{
					
				}
			}
			
			
		}
	}
}
