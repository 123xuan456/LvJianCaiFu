package com.ryan.slidefragment.tourongzi.ben;

import java.io.Serializable;

/**
 * Created by de on 2016/8/11.
 */
public class Dialog_ben implements Serializable{
    private String biao;

    private String wanshan;

    private int code;

    private String wang;

    public void setBiao(String biao){
        this.biao = biao;
    }
    public String getBiao(){
        return this.biao;
    }
    public void setWanshan(String wanshan){
        this.wanshan = wanshan;
    }
    public String getWanshan(){
        return this.wanshan;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setWang(String wang){
        this.wang = wang;
    }
    public String getWang(){
        return this.wang;
    }

}
