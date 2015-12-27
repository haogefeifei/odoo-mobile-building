package com.haogefeifei.odoo.service;

import com.haogefeifei.odoo.api.OdooApi;
import com.haogefeifei.odoo.api.inf.OdooRpcException;
import com.haogefeifei.odoo.api.inf.RpcApiInf;
import com.haogefeifei.odoo.entity.User;
import com.haogefeifei.odoo.service.inf.UserServiceInf;

import java.util.HashMap;

/**
 * 用户业务实现接口
 * Created by feifei on 15/12/28.
 */
public class UserServiceImpl implements UserServiceInf {

    private static String TAG = "UserServiceImpl";

    private RpcApiInf rpc;

    public UserServiceImpl(){
        rpc = new OdooApi();
    }

    @Override
    public int Login() throws OdooRpcException {
        return rpc.Connect();
    }

    @Override
    public int Login(String username, String password) throws OdooRpcException {
        return rpc.Connect(username, password);
    }

    @Override
    public User getUserInfo(int uid) throws OdooRpcException {

        User user = new User();
        Object[] ids = {uid}; //设置查询的id
        Object[] fields = {"partner_id", "image_medium"};  //设置查询的字段
        try {
            Object[] obj = rpc.readData(User.MODEL_NAME, ids, fields);

            if (obj != null) {
                for (Object o : obj) {
                    HashMap h = (HashMap) o;
                    user.setId(uid);
                    if (h.get("image_medium") instanceof String) {
                        user.setImage((String) h.get("image_medium"));
                    }
                    Object[] opartner = (Object[]) h.get("partner_id");
                    int i = 0;
                    for (Object p : opartner) {
                        if (i == 1) {
                            user.setUsername(p.toString());
                        }
                        i++;
                    }
                }
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
