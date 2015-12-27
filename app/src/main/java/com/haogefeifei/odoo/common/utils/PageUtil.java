package com.haogefeifei.odoo.common.utils;

/*
 * 分页信息辅助类
 */
public class PageUtil {

    //设置起始点，需要每页显示多少，当前页
    public static int getBeginIndex(int everyPage, int currentPage) {
        return (currentPage - 1) * everyPage;
    }

}
