package com.haogefeifei.odoo.api.inf;

import com.haogefeifei.odoo.common.utils.LogUtil;
import com.haogefeifei.odoo.common.utils.StringCutOut;

/**
 * RPC异常类
 * Created by feifei on 15/12/27.
 */
public class OdooRpcException extends Exception {

    private static final long serialVersionUID = 1L;

    private String exceptionMessage = ""; //错误信息

    public static final String XML_ERROR = "服务器暂时无法您的处理请求";
    public static final String FOUND_SERVER_ERROR = "无法与服务器通信";
    public static final String NULL_DATE_ERROR = "无法获取数据";
    public static final String NOT_HTTP = "没有网络";
    public static final String TIME_OUT = "请求超时";

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getMessage(){
        return exceptionMessage;
    }

    public OdooRpcException(String message) {

        super(message);

        //对错误信息进行处理
        LogUtil.d("ExceptionMessageUtil", message);

        String msg = "";

        if(message.indexOf("--") > -1){
            msg  = StringCutOut.cutOut(message, message.indexOf("-- ") + 3, message.length());
        }
        else if(message.indexOf("HTTP server returned unexpected status: Not Found") > -1
                || message.indexOf("org.apache.http.conn.HttpHostConnectException") > -1){
            msg = FOUND_SERVER_ERROR;
        }
        else if(message.indexOf("Cannot serialize null") > -1){
            msg = NULL_DATE_ERROR;
        }
        else if(message.indexOf("Failed to read server's response: failed to connect") > -1
                || message.indexOf("Null values aren't supported") > -1
                || message.indexOf("ConnectTimeoutException") > -1
                || message.indexOf("SocketTimeoutException") > -1){

            msg = TIME_OUT;

        }
        else{
            msg = message;
        }

        this.exceptionMessage = msg;

    }

}

