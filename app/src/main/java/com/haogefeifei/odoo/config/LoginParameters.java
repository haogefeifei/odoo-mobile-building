package com.haogefeifei.odoo.config;

/**
 * 登陆返回的状态
 * @author feifei
 */
public class LoginParameters {

    public static final int START = 0; //开始登陆
    public static final int SUCCESS = 1; //登陆成功
    public static final int FAILURE = -1; //登陆失败
    public static final int ERROR_XML = -2; //登陆XML-RPC错误
    public static final int ERROR_UNKNOWN = -3; //登陆未知错误
    public static final int NULL = -4; //用户名密码为空
    public static final int ERROR_AUTHORITY = -5; //用户没有登录权限
}