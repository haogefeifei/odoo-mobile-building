package com.haogefeifei.odoo.ui.activity;

import android.content.Intent;
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
import com.haogefeifei.odoo.ui.adapter.LargeAdapter;
import com.haogefeifei.odoo.ui.activity.base.BaseActivity;
import com.haogefeifei.odoo.ui.view.FastScroller;

public class PartnerListActivity extends BaseActivity {

    public static final String TAG = "PartnerListActivity";

    private LargeAdapter adapter ;

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
            case R.id.action_search:
                startActivity(new Intent(PartnerListActivity.this, PartnerSearchActivity.class));
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

        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_partner_list);
        adapter = new LargeAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FastScroller fastScroller=(FastScroller)findViewById(R.id.fast_scroller);
        fastScroller.setRecyclerView(recyclerView);

        //test code
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                adapter.getItems().addAll(java.util.Arrays.asList(getResources().getStringArray(R.array.partner_data)));

                PartnerListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        }).start();
    }

}
