package com.haogefeifei.odoo.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.haogefeifei.odoo.utils.LogOldUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * 基础
 * Created by aaa on 15/7/2.
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
    
}
