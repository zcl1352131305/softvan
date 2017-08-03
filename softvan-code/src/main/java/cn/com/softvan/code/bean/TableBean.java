package cn.com.softvan.code.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class TableBean {
    private List<ColumnBean> tableColumes;

    private String dbName;

    private String tableName;

    public List<ColumnBean> getTableColumes() {
        return tableColumes;
    }

    public void setTableColumes(List<ColumnBean> tableColumes) {
        this.tableColumes = tableColumes;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
