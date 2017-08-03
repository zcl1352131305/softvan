package cn.com.softvan.code.bean;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class ColumnBean {
    /**字段名数据库名称*/
    private String columsNameDB;
    /**字段名属性名称*/
    private String columnNameCLASS;
    /**字段备注*/
    private String columsNote;
    /**字段类型*/
    private String columsType;
    /**字段默认值*/
    private String columsValue;
    /**字段Key PRI主键约束；UNI唯一约束；MUL可以重复。*/
    private String columsKey;
    /**字段长度*/
    private String columsLength;
    /**字段是否为空0为空 1不为空*/
    private String columsIsNull;

    /**显示列宽度*/
    private String colmd;
    /**显示列名称*/
    private String name;
    /**显示列字段*/
    private String dbcolumns;
    /**字段编辑控件类型 type 0单行文本 1多行文本 2日期控件 3单项下拉选择框 4多项下拉选择框 5单项项 6多选项 7图片上传 8富文本编辑器(简单) 9富文本编辑器(完整)*/
    private String type;

    public String getColumsNameDB() {
        return columsNameDB;
    }

    public void setColumsNameDB(String columsNameDB) {
        this.columsNameDB = columsNameDB;
    }

    public String getColumnNameCLASS() {
        return columnNameCLASS;
    }

    public void setColumnNameCLASS(String columnNameCLASS) {
        this.columnNameCLASS = columnNameCLASS;
    }

    public String getColumsNote() {
        return columsNote;
    }

    public void setColumsNote(String columsNote) {
        this.columsNote = columsNote;
    }

    public String getColumsType() {
        return columsType;
    }

    public void setColumsType(String columsType) {
        this.columsType = columsType;
    }

    public String getColumsValue() {
        return columsValue;
    }

    public void setColumsValue(String columsValue) {
        this.columsValue = columsValue;
    }

    public String getColumsKey() {
        return columsKey;
    }

    public void setColumsKey(String columsKey) {
        this.columsKey = columsKey;
    }

    public String getColumsLength() {
        return columsLength;
    }

    public void setColumsLength(String columsLength) {
        this.columsLength = columsLength;
    }

    public String getColumsIsNull() {
        return columsIsNull;
    }

    public void setColumsIsNull(String columsIsNull) {
        this.columsIsNull = columsIsNull;
    }

    public String getColmd() {
        return colmd;
    }

    public void setColmd(String colmd) {
        this.colmd = colmd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbcolumns() {
        return dbcolumns;
    }

    public void setDbcolumns(String dbcolumns) {
        this.dbcolumns = dbcolumns;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
