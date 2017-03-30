package com.ryan.slidefragment.tourongzi.ben;

import java.util.List;
public class Root {
	public String di;

	public int code;

	public Date date;

	public void setDi(String di){
		this.di = di;
	}
	public String getDi(){
		return this.di;
	}
	public void setCode(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public Date getDate(){
		return this.date;
	}


	@Override
	public String toString() {
		return "Root [di=" + di + ", code=" + code + ", date=" + date + "]";
	}


	public class Date {
		public List<Guquan> guquan ;

		public void setGuquan(List<Guquan> guquan){
			this.guquan = guquan;
		}
		public List<Guquan> getGuquan(){
			return this.guquan;
		}

		public class Guquan {
			private String amount;

			private String areaid;

			private String cityid;

			private String createtime;

			private String datumtypeid;

			private String description;

			private String fangshitypeid;

			private String hangyetypeid;

			private int id;

			private String image;

			private String jingzichan;

			private String provinceid;

			private String remarks;

			private String superiority;

			private int tiaoshu;

			private String title;

			private String total;

			private int usersid;

			private String yingyee;

			private String yongtu;

			public void setAmount(String amount){
				this.amount = amount;
			}
			public String getAmount(){
				return this.amount;
			}
			public void setAreaid(String areaid){
				this.areaid = areaid;
			}
			public String getAreaid(){
				return this.areaid;
			}
			public void setCityid(String cityid){
				this.cityid = cityid;
			}
			public String getCityid(){
				return this.cityid;
			}
			public void setCreatetime(String createtime){
				this.createtime = createtime;
			}
			public String getCreatetime(){
				return this.createtime;
			}
			public void setDatumtypeid(String datumtypeid){
				this.datumtypeid = datumtypeid;
			}
			public String getDatumtypeid(){
				return this.datumtypeid;
			}
			public void setDescription(String description){
				this.description = description;
			}
			public String getDescription(){
				return this.description;
			}
			public void setFangshitypeid(String fangshitypeid){
				this.fangshitypeid = fangshitypeid;
			}
			public String getFangshitypeid(){
				return this.fangshitypeid;
			}
			public void setHangyetypeid(String hangyetypeid){
				this.hangyetypeid = hangyetypeid;
			}
			public String getHangyetypeid(){
				return this.hangyetypeid;
			}
			public void setId(int id){
				this.id = id;
			}
			public int getId(){
				return this.id;
			}
			public void setImage(String image){
				this.image = image;
			}
			public String getImage(){
				return this.image;
			}
			public void setJingzichan(String jingzichan){
				this.jingzichan = jingzichan;
			}
			public String getJingzichan(){
				return this.jingzichan;
			}
			public void setProvinceid(String provinceid){
				this.provinceid = provinceid;
			}
			public String getProvinceid(){
				return this.provinceid;
			}
			public void setRemarks(String remarks){
				this.remarks = remarks;
			}
			public String getRemarks(){
				return this.remarks;
			}
			public void setSuperiority(String superiority){
				this.superiority = superiority;
			}
			public String getSuperiority(){
				return this.superiority;
			}
			public void setTiaoshu(int tiaoshu){
				this.tiaoshu = tiaoshu;
			}
			public int getTiaoshu(){
				return this.tiaoshu;
			}
			public void setTitle(String title){
				this.title = title;
			}
			public String getTitle(){
				return this.title;
			}
			public void setTotal(String total){
				this.total = total;
			}
			public String getTotal(){
				return this.total;
			}
			public void setUsersid(int usersid){
				this.usersid = usersid;
			}
			public int getUsersid(){
				return this.usersid;
			}
			public void setYingyee(String yingyee){
				this.yingyee = yingyee;
			}
			public String getYingyee(){
				return this.yingyee;
			}
			public void setYongtu(String yongtu){
				this.yongtu = yongtu;
			}
			public String getYongtu(){
				return this.yongtu;
			}
			@Override
			public String toString() {
				return "Guquan [amount=" + amount + ", areaid=" + areaid
						+ ", cityid=" + cityid + ", createtime=" + createtime
						+ ", datumtypeid=" + datumtypeid + ", description="
						+ description + ", fangshitypeid=" + fangshitypeid
						+ ", hangyetypeid=" + hangyetypeid + ", id=" + id
						+ ", image=" + image + ", jingzichan=" + jingzichan
						+ ", provinceid=" + provinceid + ", remarks=" + remarks
						+ ", superiority=" + superiority + ", tiaoshu="
						+ tiaoshu + ", title=" + title + ", total=" + total
						+ ", usersid=" + usersid + ", yingyee=" + yingyee
						+ ", yongtu=" + yongtu + "]";
			}

		}

	}
}