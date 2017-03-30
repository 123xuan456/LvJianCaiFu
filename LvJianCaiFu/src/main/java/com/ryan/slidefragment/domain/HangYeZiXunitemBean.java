package com.ryan.slidefragment.domain;


public class HangYeZiXunitemBean {

	private String t_hangyezixun_xinwenbiaoti;
	private String t_hangyezixun_xinwenneirong;

	// private Drawable pic;
	// private String t_gongsijianjie;

	public String getT_hangyezixun_xinwenbiaoti() {
		return t_hangyezixun_xinwenbiaoti;
	}

	public void setT_hangyezixun_xinwenbiaoti(String t_hangyezixun_xinwenbiaoti) {
		this.t_hangyezixun_xinwenbiaoti = t_hangyezixun_xinwenbiaoti;
	}

	public String getT_hangyezixun_xinwenneirong() {
		return t_hangyezixun_xinwenneirong;
	}

	public void setT_hangyezixun_xinwenneirong(
			String t_hangyezixun_xinwenneirong) {
		this.t_hangyezixun_xinwenneirong = t_hangyezixun_xinwenneirong;
	}

	// 其他的地方用的数据
	private String Title;

	private String Content;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
