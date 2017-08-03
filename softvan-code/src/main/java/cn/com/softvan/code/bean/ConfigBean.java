package cn.com.softvan.code.bean;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class ConfigBean {

    /** 后台项目路径 */
    private String targetProject;

    /** mysql jdriver */
    private String driverClass;

    /** mysql 链接地址 */
    private String connectionURL;

    /** mysql userID */
    private String userId;

    /** mysql password */
    private String password;

    /** mysql 表名 */
    private String tableName;

    /** 生成对象名 */
    private String objectName;

    /** 是否生产controller */
    private String isController;

    /** 是否生产service */
    private String isService;

    /** 是否生产model */
    private String isModel;

    /** 是否生产dao */
    private String isDao;

    /** 基础包名 */
    private String basePackage;


    /** 模块包名 */
    private String modulePackage;

    /** 表信息 */
    private TableBean table;

    /** 模块名称 */
    private String moduleName;

    /** java路径 */
    private String javaLocation;

    /** java路径 */
    private String javaBeanLocation;

    /** java路径 */
    private String javaDaoLocation;

    /** java路径 */
    private String javaMapperXmlLocation;

    /** java路径 */
    private String javaServiceLocation;

    /** java路径 */
    private String javaControllerLocation;



    /** 配置文件路径 */
    private String resLocation;

    /** 前端nodejs路径 */
    private String fTargetProject;

    /** 前端页面命名前缀 */
    private String fEjsPrefix;

    /** 前端页面显示列名 */
    private String fShowColumn;

    /** 是否生成前端 */
    private String isFront;

    public String getJavaMapperXmlLocation() {
        return javaMapperXmlLocation;
    }

    public void setJavaMapperXmlLocation(String javaMapperXmlLocation) {
        this.javaMapperXmlLocation = javaMapperXmlLocation;
    }

    public String getJavaBeanLocation() {
        return javaBeanLocation;
    }

    public void setJavaBeanLocation(String javaBeanLocation) {
        this.javaBeanLocation = javaBeanLocation;
    }

    public String getJavaDaoLocation() {
        return javaDaoLocation;
    }

    public void setJavaDaoLocation(String javaDaoLocation) {
        this.javaDaoLocation = javaDaoLocation;
    }

    public String getJavaServiceLocation() {
        return javaServiceLocation;
    }

    public void setJavaServiceLocation(String javaServiceLocation) {
        this.javaServiceLocation = javaServiceLocation;
    }

    public String getJavaControllerLocation() {
        return javaControllerLocation;
    }

    public void setJavaControllerLocation(String javaControllerLocation) {
        this.javaControllerLocation = javaControllerLocation;
    }

    public String getIsFront() {
        return isFront;
    }

    public void setIsFront(String isFront) {
        this.isFront = isFront;
    }

    public String getfShowColumn() {
        return fShowColumn;
    }

    public void setfShowColumn(String fShowColumn) {
        this.fShowColumn = fShowColumn;
    }

    public String getfTargetProject() {
        return fTargetProject;
    }

    public void setfTargetProject(String fTargetProject) {
        this.fTargetProject = fTargetProject;
    }

    public String getfEjsPrefix() {
        return fEjsPrefix;
    }

    public void setfEjsPrefix(String fEjsPrefix) {
        this.fEjsPrefix = fEjsPrefix;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public TableBean getTable() {
        return table;
    }

    public void setTable(TableBean table) {
        this.table = table;
    }

    public String getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(String targetProject) {
        this.targetProject = targetProject;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getIsController() {
        return isController;
    }

    public void setIsController(String isController) {
        this.isController = isController;
    }

    public String getIsService() {
        return isService;
    }

    public void setIsService(String isService) {
        this.isService = isService;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }


    public String getModulePackage() {
        return modulePackage;
    }

    public void setModulePackage(String modulePackage) {
        this.modulePackage = modulePackage;
    }

    public String getJavaLocation() {
        return javaLocation;
    }

    public void setJavaLocation(String javaLocation) {
        this.javaLocation = javaLocation;
    }

    public String getResLocation() {
        return resLocation;
    }

    public String getIsModel() {
        return isModel;
    }

    public void setIsModel(String isModel) {
        this.isModel = isModel;
    }

    public String getIsDao() {
        return isDao;
    }

    public void setIsDao(String isDao) {
        this.isDao = isDao;
    }

    public void setResLocation(String resLocation) {
        this.resLocation = resLocation;
    }
}
