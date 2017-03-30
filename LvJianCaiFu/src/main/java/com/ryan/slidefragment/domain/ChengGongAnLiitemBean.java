package com.ryan.slidefragment.domain;


public class ChengGongAnLiitemBean {

	private String t_chenggonganli_xinwenbiaoti;
	private String t_chenggonganli_xinwenneirong;

	// private Drawable pic;
	// private String t_gongsijianjie;

	public String getT_chenggonganli_xinwenbiaoti() {
		return t_chenggonganli_xinwenbiaoti;
	}

	public void setT_chenggonganli_xinwenbiaoti(
			String t_chenggonganli_xinwenbiaoti) {
		this.t_chenggonganli_xinwenbiaoti = t_chenggonganli_xinwenbiaoti;
	}

	public String getT_chenggonganli_xinwenneirong() {
		return t_chenggonganli_xinwenneirong;
	}

	public void setT_chenggonganli_xinwenneirong(
			String t_chenggonganli_xinwenneirong) {
		this.t_chenggonganli_xinwenneirong = t_chenggonganli_xinwenneirong;
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
