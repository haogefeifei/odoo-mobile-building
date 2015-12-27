package com.haogefeifei.odoo.service.inf;

import com.haogefeifei.odoo.api.inf.OdooRpcException;
import com.haogefeifei.odoo.entity.User;

/**
 * Created by feifei on 15/12/28.
 */
public interface UserServiceInf {

    /**
     * 用户登录
     *
     * @return 登陆成功的id ，失败则返回负数代码
     */
    public int Login() throws OdooRpcException;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     * @throws OdooRpcException
     */
    public int Login(String username, String password) throws OdooRpcException;


    /**
     * 通过用户id来获取用户信息
     *
     * @param uid 用户id
     * @return User对象
     */
    public User getUserInfo(int uid) throws OdooRpcException;

}
