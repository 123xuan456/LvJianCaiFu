package com.ryan.slidefragment.domain;


public class JiShuZhanShiitemBean {

	private String t_jishuzhanshi_xinwenbiaoti;
	private String t_jishuzhanshi_xinwenneirong;

	// private Drawable pic;
	// private String t_gongsijianjie;

	public String getT_jishuzhanshi_xinwenbiaoti() {
		return t_jishuzhanshi_xinwenbiaoti;
	}

	public void setT_jishuzhanshi_xinwenbiaoti(
			String t_jishuzhanshi_xinwenbiaoti) {
		this.t_jishuzhanshi_xinwenbiaoti = t_jishuzhanshi_xinwenbiaoti;
	}

	public String getT_jishuzhanshi_xinwenneirong() {
		return t_jishuzhanshi_xinwenneirong;
	}

	public void setT_jishuzhanshi_xinwenneirong(
			String t_jishuzhanshi_xinwenneirong) {
		this.t_jishuzhanshi_xinwenneirong = t_jishuzhanshi_xinwenneirong;
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
