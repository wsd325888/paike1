package com.zm.paipai.proj;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/27.
 */
public class ListactivityBean {
    public int status;
    public ArrayList<PingLun> pinglunlist;


    public static class PingLun{

        public  int plId;
        public String name;
        private String photoImg;
        public String content;
        public String dianzan;

        @Override
        public String toString() {
            return "PingLun{" +
                    "plId=" + plId +
                    ", name='" + name + '\'' +
                    ", photoImg='" + photoImg + '\'' +
                    ", content='" + content + '\'' +
                    ", dianzan='" + dianzan + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ListactivityBean{" +
                "status=" + status +
                ", pinglunList=" + pinglunlist +
                '}';
    }
}
