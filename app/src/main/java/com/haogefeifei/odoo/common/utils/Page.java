package com.haogefeifei.odoo.common.utils;

/**
 * 分页对象
 *
 * @author Dev04
 */
public class Page {

    // 1.每页显示数量(everyPage)
    private int everyPage;
    //2.当前页(currentPage)
    private int currentPage;

    public Page(int everyPage, int currentPage) {
        this.everyPage = everyPage;
        this.currentPage = currentPage;
    }

    //构造函数，默认
    //构造方法，对所有属性进行设置
    public Page() {
        this.currentPage = 1;
        this.everyPage = 30;
    }

    public int getEveryPage() {
        return everyPage;
    }

    public void setEveryPage(int everyPage) {
        this.everyPage = everyPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


}
