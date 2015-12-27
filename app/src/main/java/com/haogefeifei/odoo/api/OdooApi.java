package com.haogefeifei.odoo.api;

import com.haogefeifei.odoo.api.inf.OdooRpcException;
import com.haogefeifei.odoo.api.inf.RpcApiInf;
import com.haogefeifei.odoo.common.utils.LogUtil;
import com.haogefeifei.odoo.common.utils.Page;
import com.haogefeifei.odoo.config.LoginParameters;

import org.xmlrpc.android.XMLRPCClient;
import org.xmlrpc.android.XMLRPCException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * 提供OdooORM的操作方法
 * Created by feifei on 15/12/28.
 */
public class OdooApi implements RpcApiInf{

    private static String TAG = "OdooApi";

    private static OdooApi instance = new OdooApi();

    public URL mUrl;
    private OdooConnect conn;  //XML-RPC连接对象
    private HashMap<String, Object> contexts ; //上下文

    public static OdooApi getInstance() {
        return instance;
    }

    public OdooApi() {

        this.conn = OdooConnect.getInstance();
        this.contexts = new HashMap<String, Object>();
        this.contexts.put("lang", "zh_CN");
    }

    @Override
    public int Connect() throws OdooRpcException {
        
        Object id;
        try {
            URL loginUrl = new URL("http", conn.getHost(), conn.getPort(), "/xmlrpc/common");

            if(conn.getPort() == 443){
                loginUrl = new URL("https", conn.getHost(), conn.getPort(), "/xmlrpc/common");
            }

            XMLRPCClient client = new XMLRPCClient(loginUrl);
            id = client.call("login", conn.getDb(), conn.getAccount(), conn.getPassword());
            if (id instanceof Integer) {
                this.conn.setUid((Integer) id);
                return (Integer) id;

            }

            return LoginParameters.FAILURE;
        } catch (XMLRPCException e) {
            LogUtil.w(TAG, "Connect--->" + e.toString());
            throw new OdooRpcException(e.getMessage());
        } catch (MalformedURLException e) {
            LogUtil.w(TAG, "Connect--->" + e.toString());
            return LoginParameters.ERROR_UNKNOWN;
        } catch (ClassCastException e) {
            LogUtil.w(TAG, "Connect--->" + e.toString());
            return LoginParameters.ERROR_UNKNOWN;
        }
    }

    @Override
    public int Connect(String username, String password) throws OdooRpcException {

        this.conn.setAccount(username);
        this.conn.setPassword(password);

        return this.Connect();
    }

    @Override
    public int confirmationPassWord(String password) throws OdooRpcException {
        //TODO
        return 0;
    }

    @Override
    public Object getCount(String model, Object[] filters) throws OdooRpcException {
        //TODO
        return null;
    }

    @Override
    public int createObject(String model, HashMap<String, Object> values) throws OdooRpcException {
        //TODO
        return 0;
    }

    @Override
    public Object writeObject(String model, Object[] ids, HashMap<String, Object> values) throws OdooRpcException {
        //TODO
        return null;
    }

    @Override
    public Object[] readData(String model, Object[] ids, Object[] fields) throws OdooRpcException {
        //TODO
        return new Object[0];
    }

    @Override
    public Object[] searchObject(String model, Object[] filters) throws OdooRpcException {
        //TODO
        return new Object[0];
    }

    @Override
    public Object[] searchObject(String model, Object[] filters, Page page) throws OdooRpcException {
        //TODO
        return new Object[0];
    }

    @Override
    public Object callWorkflow(String model, int modelid, String wkfName) throws OdooRpcException {
        //TODO
        return null;
    }

    @Override
    public Object call(String model, String method, Object... parameters) throws OdooRpcException {
        //TODO
        return null;
    }
}
