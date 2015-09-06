package com.haogefeifei.odoo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.haogefeifei.odoo.R;
import com.haogefeifei.odoo.ui.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        MobclickAgent.updateOnlineConfig(this); //友盟统计

        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        this.finish();
    }
}
