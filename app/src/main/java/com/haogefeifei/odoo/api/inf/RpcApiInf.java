package com.haogefeifei.odoo.api.inf;

import com.haogefeifei.odoo.common.utils.Page;

import java.util.HashMap;

/**
 * Created by feifei on 15/12/27.
 */
public interface RpcApiInf {

    /**
     * 登录
     *
     * @return 结果代码
     */
    public int Connect() throws OdooRpcException;


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     * @throws OdooRpcException
     */
    public int Connect(String username, String password) throws OdooRpcException;


    /**
     * 登录验证
     *
     * @param password
     * @return
     */
    public int confirmationPassWord(String password) throws OdooRpcException;

    /**
     * 查询记录总数
     *
     * @param model
     * @param filters
     * @return
     */
    public Object getCount(String model, Object[] filters) throws OdooRpcException;


    /**
     * 创建对象
     *
     * @param model
     * @param values
     * @return
     */
    public int createObject(String model, HashMap<String, Object> values) throws OdooRpcException;


    /**
     * 修改
     *
     * @param model
     * @param values
     * @return
     */
    public Object writeObject(String model, Object[] ids, HashMap<String, Object> values) throws OdooRpcException;


    /**
     * 读取数据
     *
     * @param model
     * @param ids
     * @param fields
     * @return
     */
    public Object[] readData(String model, Object[] ids, Object[] fields) throws OdooRpcException;


    /**
     * 查询
     *
     * @param model   要查询的对象
     * @param filters 查询过滤条件
     */
    public Object[] searchObject(String model, Object[] filters) throws OdooRpcException;

    /**
     * 查询
     *
     * @param model   要查询的对象
     * @param filters 查询过滤条件
     * @param page    分页对象
     */
    public Object[] searchObject(String model, Object[] filters, Page page) throws OdooRpcException;

    /**
     * 触发对象的工作流
     *
     * @param model   OE模型名称
     * @param modelid 要操作的对象id
     * @param wkfName 工作流名称
     * @return
     */
    public Object callWorkflow(String model, int modelid, String wkfName) throws OdooRpcException;


    /**
     * 触发对象里面的方法
     *
     * @param model      OE模型名称
     * @param method     要操作的对象id
     * @param parameters 工作流名称
     * @return
     */
    public Object call(String model, String method, Object... parameters) throws OdooRpcException;
}
