package com.haogefeifei.odoo.api;

import com.haogefeifei.odoo.application.App;

/**
 * XmlRPC访问Odoo连接参数对象
 *
 * @author feifei
 */
public class OdooConnect {

    private static OdooConnect instance = new OdooConnect();

    public static OdooConnect getInstance() {

        return App.getInstance().getInstanceConnect();
    }

    public static OdooConnect getInstanceApplication() {
        return instance;
    }

    private Long uid;    //用户id <--登录的时候不需要
    private String facName; //工厂名称
    private String userName;
    private String host; //服务器地址
    private int port; //端口
    private String db; //数据库
    private String account; //用户账户
    private String password; //用户密码
    private String image;

    private OdooConnect() {


    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
