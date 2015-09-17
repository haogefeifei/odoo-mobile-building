package com.haogefeifei.odoo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.haogefeifei.odoo.R;

public class PartnerSearchActivity extends Activity {

    public static final String TAG = "PartnerSearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupView();
    }

    private void setupView(){

    }

}
