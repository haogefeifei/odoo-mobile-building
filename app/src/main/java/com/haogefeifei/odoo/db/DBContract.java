package com.haogefeifei.odoo.db;

/**
 * Created by feifei on 15/9/19.
 */
public class DBContract {

    /**
     * 版本管理表字段
     */
    interface VersionColumns {

        String VERSION_VALUE = "version_value";
        String VERSION_KEY = "version_key";
    }

    /**
     * 客户表字段
     */
    interface PartnerColumns {

        String PARTNER_ID = "partner_id";                   //id
        String PARTNER_TYPE = "partner_type";               //类型
        String PARTNER_NAME = "partner_name";               //客户名称
        String PARTNER_PARENT_ID = "partner_parent_id";     //父id
        String PARTNER_COUNTRY = "partner_country";         //国家
        String PARTNER_STATE = "partner_state";             //省份
        String PARTNER_CITY = "partner_city";               //城市
        String PARTNER_ZIP = "partner_zip";                 //邮编
        String PARTNER_STREET = "partner_street";           //街道
        String PARTNER_STREET2 = "partner_street2";         //街道2
        String PARTNER_EMAIL = "partner_email";             //邮箱
        String PARTNER_PHONE = "partner_phone";             //邮箱
        String PARTNER_FAX = "partner_fax";                 //传真
        String PARTNER_MOBILE = "partner_mobile";           //手机
        String PARTNER_IMAGE = "partner_image";             //头像
    }

}
