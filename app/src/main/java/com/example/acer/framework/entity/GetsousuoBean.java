package com.example.acer.framework.entity;

import java.util.ArrayList;

/**
 * Created by acer on 2016/9/27.
 */
public class GetsousuoBean {
    public ArrayList<Personinfo> sslist;

    @Override
    public String toString() {
        return "GetsousuoBean{" +
                "sslist=" + sslist +
                '}';
    }


    public static class Sousuo {
        public String id;
        public String name;

        @Override
        public String toString() {
            return "Sousuo{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
