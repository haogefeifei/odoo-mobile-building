package com.haogefeifei.odoo.db.biz;

import android.content.Context;
import android.database.Cursor;

import com.haogefeifei.odoo.db.DBContract.*;
import com.haogefeifei.odoo.db.biz.base.DbBase;
import com.haogefeifei.odoo.db.DBHelper.*;

/**
 * 版本信息数据操作类
 * Created by feifei on 15/9/20.
 */
public class DBVersion extends DbBase {

    public DBVersion(Context context) {
        super(context);
    }

    /**
     * 通过key来获取对应的信息
     * @param key
     * @return
     */
    public String getVersion(String key) {

        String sql = "select * from " + Tables.VERSION + " where "
                + VersionColumns.VERSION_KEY + "='" + key + "' ";

        Cursor c = db.rawQuery(sql, null);
        String value = null;

        if(c.getCount() > 0){
            c.moveToFirst();
            value = c.getString(c.getColumnIndex(VersionColumns.VERSION_VALUE));
        }

        c.close();

        return value;
    }

    /**
     * 设置版本信息
     * @param key
     * @param value
     */
    public void setVersion(String key, String value) {

        String sql = "update [" + Tables.VERSION + "] set "
                + VersionColumns.VERSION_VALUE + " = '" + value
                + "' where " + VersionColumns.VERSION_KEY
                + "= '" + key + "' ";//修改的SQL语句
        db.execSQL(sql);//执行修改

    }
}
