package com.ryan.slidefragment.domain;


import android.graphics.drawable.Drawable;

public class PullBean {
	
	private String sale_num;
	private Drawable pic;
	private String name;
	private String renzheng;
	
	//其他的地方用的数据
	private String Title;
	private String Content;
	private String url;
	private String id;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String geturl() {
		return url;
	}
	public void seturl(String url) {
		this.url = url;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getSale_num() {
		return sale_num;
	}
	public void setSale_num(String sale_num) {
		this.sale_num = sale_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(String renzheng) {
		this.renzheng = renzheng;
	}
	
	
	public Drawable getPic() {
		return pic;
	}
	public void setPic(Drawable pic) {
		this.pic = pic;
	}


}
