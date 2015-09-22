package com.haogefeifei.odoo.db.biz.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.haogefeifei.odoo.db.DBHelper;

/**
 * Created by feifei on 15/9/22.
 */
public class DbBase {

    protected static DBHelper helper;
    protected static SQLiteDatabase db;

    public DbBase(Context context) {

        if(helper==null) {
            helper = new DBHelper(context);
        }

        if(db==null || !db.isOpen()) {
            db = helper.getWritableDatabase();
        }

    }
}
