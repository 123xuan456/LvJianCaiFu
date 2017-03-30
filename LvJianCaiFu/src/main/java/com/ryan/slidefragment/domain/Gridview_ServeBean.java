package com.ryan.slidefragment.domain;

import java.io.Serializable;
import java.util.List;

/**
 * �����е�JavaBean
 * @author lyz
 *
 */

public class Gridview_ServeBean implements Serializable{
	private static final long serialVersionUID = 1L;
	public int code;

	public Date date;
	public class Date {
		public List<Fuwu> fuwu ;
		public class Fuwu {
			public int id;
			public String image;//����ͼƬ��ַ
			public String name;//����
			}

		}


}
