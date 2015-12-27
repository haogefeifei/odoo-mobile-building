package com.haogefeifei.odoo.entity;

import com.haogefeifei.odoo.entity.base.OdooModel;

import java.sql.Timestamp;

/**
 * 用户对象
 * Created by feifei on 15/12/28.
 */
public class User extends OdooModel {

    public static final String MODEL_NAME = "res.users";

    private String username ; // 用户名
    private String login ; //用户登录账号
    private String password ; //用户密码
    private String image ;
    private Timestamp loginTime ;
    private Object[] em_ids;

    public Object[] getEm_ids() {

        if(em_ids == null){
            em_ids = new Object[0];
        }

        return em_ids;
    }

    public void setEm_ids(Object[] em_ids) {
        this.em_ids = em_ids;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogin() {
        return login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
