package com.e3e4e20.entity.service;

/*
Filename: PageResult
Created: 2023年04月11日 06时50分51秒 星期二
Author: 天龙梦雪
*/

import java.util.List;

/**
 * 分页对象
 *      {
 *          "code": Integer 200
 *          "info": true
 *          "message": String "查询成功！"
 *          "data": Object {
 *              total: Long //总条数
 *              rows: List<T> //数据列表
 *          }
 *      }
 */
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
