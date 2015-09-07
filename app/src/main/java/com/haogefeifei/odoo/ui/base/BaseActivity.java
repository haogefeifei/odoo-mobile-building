package com.haogefeifei.odoo.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

import com.haogefeifei.odoo.utils.LogOldUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * 基础
 * Created by feifei on 15/7/2.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        LogOldUtil.d(TAG, this.getClass().getSimpleName() + " onStart() invoked!!");
        super.onStart();
    }

    protected void onRestart() {

        super.onRestart();
    }

    @Override
    public void onResume() {
        LogOldUtil.d(TAG, this.getClass().getSimpleName() + " onResume() invoked!!");
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        LogOldUtil.d(TAG, this.getClass().getSimpleName() + " onPause() invoked!!");
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Override
    protected void onStop() {
        LogOldUtil.d(TAG, this.getClass().getSimpleName() + " onStop() invoked!!");
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        LogOldUtil.d(TAG, this.getClass().getSimpleName()
                + " onDestroy() invoked!!");
        super.onDestroy();
    }

    protected void transparentBaseUI(){

        if (android.os.Build.VERSION.SDK_INT > 18) {
            Window window = getWindow();
//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置根布局的内边距
//            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.layout);
//            relativeLayout.setPadding(0, getActionBarHeight() + getStatusBarHeight(), 0, 0);

            //判断是否有虚拟按键
//            if (checkDeviceHasNavigationBar(this)) {
//
//                Log.d(TAG, "------->有虚拟按键!");
//                FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.main_fab);
//                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
//                lp.setMargins(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin + getNavigationBarHeight(this));
//                floatingActionButton.setLayoutParams(lp);
//
//            }
        }
    }

    protected static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = 0;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
        if (id > 0) {
            navigationBarHeight = rs.getDimensionPixelSize(id);
        }
        return navigationBarHeight;
    }

    @SuppressLint("NewApi")
    protected static boolean checkDeviceHasNavigationBar(Context activity) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }
}
