package com.ryan.slidefragment.domain;

import java.io.Serializable;
import java.util.List;

public class ChengYuan_CommonalityBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String di;
	public int code;
	public Date date;
	public class Date {
		public List<Lishizhang> lishizhang ;
		public class Lishizhang {
			public String content;
			public int id;
			public String imgurl;
			public String time;
			public String title;
			}

	}

}
