package com.haogefeifei.odoo.utils;

import com.orhanobut.logger.Logger;

/**
 * Log工具类
 * @author feifei
 */
public class LogUtil {
    
    public static boolean isDebug = true;

    public static void v(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).v(msg);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (isDebug)
            Logger.t(tag).v(msg, t);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).d(msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (isDebug)
            Logger.t(tag).d(msg, t);
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).i(msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (isDebug)
            Logger.t(tag).i(msg, t);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).w(msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (isDebug)
            Logger.t(tag).w(msg, t);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Logger.t(tag).e(msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (isDebug)
            Logger.t(tag).e(msg, t);
    }

    public static void json(String tag, String json) {
        if (isDebug)
            Logger.t(tag).json(json);
    }

    public static void xml(String tag, String xml) {
        if (isDebug)
            Logger.t(tag).xml(xml);
    }
}
