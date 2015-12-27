package com.haogefeifei.odoo.entity.base;

/**
 * Created by feifei on 15/12/28.
 */
public class OdooModel {

    private Integer id ;
    private Integer operate = 4; //操作

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
