package com.zm.paipai.proj;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/30.
 */
public class PathactivityBean {
    public int status;
    public ArrayList<Path> pathList;

    public static class Path {
        public String path;
        public String descp;
        public String dianzan;

        @Override
        public String toString() {
            return "Path{" +
                    "path='" + path + '\'' +
                    ", descp='" + descp + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PathactivityBean{" +
                "status=" + status +
                ", pathList=" + pathList +
                '}';
    }
}
