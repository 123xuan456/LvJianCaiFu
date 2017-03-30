package com.ryan.slidefragment.domain;
/**
 * 首页新闻
 * @author de
 *
 */
public class HomeList {
		private int id;

		private int ntypeId;

		private String title;

		public void setId(int id){
		this.id = id;
		}
		public int getId(){
		return this.id;
		}
		public void setNtypeId(int ntypeId){
		this.ntypeId = ntypeId;
		}
		public int getNtypeId(){
		return this.ntypeId;
		}
		public void setTitle(String title){
		this.title = title;
		}
		public String getTitle(){
		return this.title;
		}

}
