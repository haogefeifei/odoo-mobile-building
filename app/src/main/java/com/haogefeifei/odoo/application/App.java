package com.haogefeifei.odoo.application;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.haogefeifei.odoo.api.OdooApi;
import com.haogefeifei.odoo.api.OdooConnect;

/**
 * Created by tangqi on 7/20/15.
 */
public class App extends Application {

    public static final String TAG = "OdooApp";

    private static App application;
    private static OdooConnect instanceConnect = OdooConnect.getInstanceApplication();

    public OdooConnect getInstanceConnect() {
        return instanceConnect;
    }

    public static App getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        initMap();
        initConnectConfig();
    }

    private void initMap() {

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

    private void initConnectConfig(){

        OdooConnect connect = OdooConnect.getInstance();
        connect.setHost("192.168.31.247");
        connect.setPort(8069);
        connect.setDb("odoo");
    }
}
