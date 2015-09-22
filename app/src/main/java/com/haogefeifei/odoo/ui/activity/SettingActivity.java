package com.haogefeifei.odoo.ui.activity;

import android.os.Bundle;

import com.haogefeifei.odoo.R;
import com.haogefeifei.odoo.ui.activity.base.ToolbarActivity;
import com.haogefeifei.odoo.ui.settings.SettingFragment;

public class SettingActivity extends ToolbarActivity {

    public static final String TAG = "SettingActivity";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getString(R.string.title_activity_setting));
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_frame, new SettingFragment())
                .commit();
    }
}
