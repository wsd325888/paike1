package com.zm.paipai.proj;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/29.
 */
public class XinxiactivityBean {
    public ArrayList<Information> informationList;

    public static class Information{
        public String photoImage;
        public String fensishu;
        public String guanzhuren;
        public String shipin;


    }


    @Override
    public String toString() {
        return "XinxiactivityBean{" +
                "informationList=" + informationList +
                '}';
    }
}
