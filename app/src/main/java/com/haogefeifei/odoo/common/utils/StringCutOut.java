package com.haogefeifei.odoo.common.utils;

/**
 * 字符串截取操作类
 *
 * @author FeiFei
 */
public class StringCutOut {

    public static String cutOut(String matter, int IndexofA, int IndexofB) {
        return matter.substring(IndexofA, IndexofB);
    }

    public static String cutOut(String matter, String str) {
        int Indexof = matter.indexOf(str);
        return matter.substring(0, Indexof);
    }

    public static String cutOut(String matter, String strA, String strB) {
        String strtempa = strA;
        String strtempb = strB;
        int IndexofA = matter.indexOf(strtempa) + strtempa.length();
        int IndexofB = matter.indexOf(strtempb);
        String s = null;
        try {
            s = matter.substring(IndexofA, IndexofB);
        } catch (Exception e) {
            e.printStackTrace();
            s = "0";
        } finally {
            return s;
        }

    }

}
