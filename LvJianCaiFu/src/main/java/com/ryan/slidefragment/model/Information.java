package com.ryan.slidefragment.model;

public class Information {

	private String title;
	private String desc;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		 this.desc = desc;
	}

	@Override
	public String toString() {
		return "Information [title=" + title + ", desc=" + desc + ",time="+ time +"]";
	}

}

