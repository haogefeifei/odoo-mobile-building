package com.haogefeifei.odoo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.haogefeifei.odoo.db.DBContract.*;
import com.haogefeifei.odoo.utils.LogOldUtil;

/**
 * 创建数据库
 * Created by feifei on 15/9/19.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    private static final String DATABASE_NAME = "odoo.db";
    private static final int CUR_DATABASE_VERSION = 1;

    private final Context mContext;

    /**
     * 数据库表
     */
    interface Tables {

        String VERSION = "version"; //数据更新时间
        String PARTNER = "partner"; //客户

    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, CUR_DATABASE_VERSION);
        mContext = context;
    }

    /**
     * 创建
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Tables.VERSION + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + VersionColumns.VERSION_KEY + " TEXT NOT NULL,"
                + VersionColumns.VERSION_VALUE + " TEXT NOT NULL)");

        db.execSQL("INSERT INTO " + Tables.VERSION
                + " VALUES(null, ?, ?)", new Object[]{"1979-01-01 01:00:00", "update_time"});

        db.execSQL("CREATE TABLE " + Tables.PARTNER + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PartnerColumns.PARTNER_ID + " INTEGER NOT NULL,"
                + PartnerColumns.PARTNER_TYPE + " TEXT,"
                + PartnerColumns.PARTNER_NAME + " TEXT NOT NULL,"
                + PartnerColumns.PARTNER_PARENT_ID + " INTEGER,"
                + PartnerColumns.PARTNER_COUNTRY + " TEXT,"
                + PartnerColumns.PARTNER_STATE + " TEXT,"
                + PartnerColumns.PARTNER_CITY + " TEXT,"
                + PartnerColumns.PARTNER_ZIP + " TEXT,"
                + PartnerColumns.PARTNER_STREET + " TEXT,"
                + PartnerColumns.PARTNER_STREET2 + " TEXT,"
                + PartnerColumns.PARTNER_EMAIL + " TEXT,"
                + PartnerColumns.PARTNER_PHONE + " TEXT,"
                + PartnerColumns.PARTNER_FAX + " TEXT,"
                + PartnerColumns.PARTNER_MOBILE + " TEXT,"
                + PartnerColumns.PARTNER_IMAGE + " TEXT)");

    }

    /**
     * 当数据库版本发生改变的时候被调用
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        LogOldUtil.d(TAG, "onUpgrade() from " + oldVersion + " to " + newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + Tables.VERSION);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.PARTNER);
    }
}
