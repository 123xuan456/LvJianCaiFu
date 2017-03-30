package com.ryan.slidefragment.domain;

import java.util.List;

/**
 * Created by de on 2017/3/14.
 */
public class LunbotuBean {

    /**
     * date : {"name":[{"id":125,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820168759.jpg"},{"id":124,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820115534.png"},{"id":123,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820102550.png"},{"id":122,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820092755.png"},{"id":121,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820084012.png"}]}
     * code : 200
     */

    private DateBean date;
    private int code;

    public void setDate(DateBean date) {
        this.date = date;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DateBean getDate() {
        return date;
    }

    public int getCode() {
        return code;
    }

    public static class DateBean {
        /**
         * name : [{"id":125,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820168759.jpg"},{"id":124,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820115534.png"},{"id":123,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820102550.png"},{"id":122,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820092755.png"},{"id":121,"mid":2,"playTime":5,"urlimg":"http://106.2.219.210:8088/upload/1470820084012.png"}]
         */

        private List<NameBean> name;

        public void setName(List<NameBean> name) {
            this.name = name;
        }

        public List<NameBean> getName() {
            return name;
        }

        public static class NameBean {
            /**
             * id : 125
             * mid : 2
             * playTime : 5
             * urlimg : http://106.2.219.210:8088/upload/1470820168759.jpg
             */

            private int id;
            private int mid;
            private int playTime;
            private String urlimg;

            public void setId(int id) {
                this.id = id;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public void setPlayTime(int playTime) {
                this.playTime = playTime;
            }

            public void setUrlimg(String urlimg) {
                this.urlimg = urlimg;
            }

            public int getId() {
                return id;
            }

            public int getMid() {
                return mid;
            }

            public int getPlayTime() {
                return playTime;
            }

            public String getUrlimg() {
                return urlimg;
            }
        }
    }
}
