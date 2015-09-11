package com.haogefeifei.odoo.ui.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.haogefeifei.odoo.R;
import com.haogefeifei.odoo.adapter.LargeAdapter;
import com.haogefeifei.odoo.ui.activity.base.BaseActivity;
import com.haogefeifei.odoo.view.FastScroller;

public class PartnerListActivity extends BaseActivity {

    public static final String TAG = "PartnerListActivity";

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_list);

        setupView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_partner_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_filter:
                mDrawerLayout.openDrawer(GravityCompat.END);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupView(){

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        LinearLayout navigationView = (LinearLayout) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_partner_list);
        recyclerView.setAdapter(new LargeAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FastScroller fastScroller=(FastScroller)findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(recyclerView);
    }

    private void setupDrawerContent(LinearLayout navigationView) {


    }
}
