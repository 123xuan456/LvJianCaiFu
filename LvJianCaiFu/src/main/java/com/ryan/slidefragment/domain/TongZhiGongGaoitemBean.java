package com.ryan.slidefragment.domain;


public class TongZhiGongGaoitemBean {

	private String t_tongzhigonggao_xinwenbiaoti;
	private String t_tongzgigonggao_xinwenneirong;
//	private String t_tongzhigonggao_time;
	// private Drawable pic;
	// private String t_gongsijianjie;


	public String getT_tongzhigonggao_xinwenbiaoti() {
		return t_tongzhigonggao_xinwenbiaoti;
	}

	public void setT_tongzhigonggao_xinwenbiaoti(
			String t_tongzhigonggao_xinwenbiaoti) {
		this.t_tongzhigonggao_xinwenbiaoti = t_tongzhigonggao_xinwenbiaoti;
	}

	public String getT_tongzgigonggao_xinwenneirong() {
		return t_tongzgigonggao_xinwenneirong;
	}

	public void setT_tongzgigonggao_xinwenneirong(
			String t_tongzgigonggao_xinwenneirong) {
		this.t_tongzgigonggao_xinwenneirong = t_tongzgigonggao_xinwenneirong;
	}

//	public String getT_tongzhigonggao_time() {
//		return t_tongzhigonggao_time;
//	}
//
//	public void setT_tongzhigonggao_time(String t_tongzhigonggao_time) {
//		this.t_tongzhigonggao_time = t_tongzhigonggao_time;
//	}

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
