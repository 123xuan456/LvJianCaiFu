package com.ryan.slidefragment.tourongzi.ben;

import java.util.List;

/**
 * Created by de on 2016/8/9.
 */
public class JuRenMai_ben {
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
    public class Date {
        private List<Jurenmai> jurenmai ;

        public void setJurenmai(List<Jurenmai> jurenmai){
            this.jurenmai = jurenmai;
        }
        public List<Jurenmai> getJurenmai(){
            return this.jurenmai;
        }

    }

    public class Jurenmai {
        private String bie;

        private int capid;

        private String content;

        private int id;

        private String ming;

        private String site;

        private String title;

        private String url;

        private String usersid;

        public void setBie(String bie){
            this.bie = bie;
        }
        public String getBie(){
            return this.bie;
        }
        public void setCapid(int capid){
            this.capid = capid;
        }
        public int getCapid(){
            return this.capid;
        }
        public void setContent(String content){
            this.content = content;
        }
        public String getContent(){
            return this.content;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setMing(String ming){
            this.ming = ming;
        }
        public String getMing(){
            return this.ming;
        }
        public void setSite(String site){
            this.site = site;
        }
        public String getSite(){
            return this.site;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }
        public void setUsersid(String usersid){
            this.usersid = usersid;
        }
        public String getUsersid(){
            return this.usersid;
        }

    }
}
