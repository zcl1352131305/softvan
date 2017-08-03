package cn.com.softvan.code;

import cn.com.softvan.code.bean.ColumnBean;
import cn.com.softvan.code.bean.ConfigBean;
import cn.com.softvan.code.bean.TableBean;
import cn.com.softvan.utils.StrUtils;
import cn.com.softvan.utils.jdbc.JdbcUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class CodeConfigInit {

    public static ConfigBean getConfig(String config_path){
        ConfigBean configBean = new ConfigBean();
        String fileurl = config_path.replaceAll("%20"," ");
        Properties pro = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileurl);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in, "utf-8"));
            pro.load(bf);

            configBean.setConnectionURL(pro.getProperty("connectionURL"));
            //configBean.setControllerPackage(pro.getProperty("controllerPackage"));
            configBean.setDriverClass(pro.getProperty("driverClass"));
            configBean.setIsController(pro.getProperty("isController"));
            configBean.setIsService(pro.getProperty("isService"));
            configBean.setModulePackage(pro.getProperty("modulePackage"));
            //configBean.setMysqlJarPath(pro.getProperty("classPath"));
            configBean.setObjectName(pro.getProperty("objectName"));
            configBean.setPassword(pro.getProperty("password"));
            //configBean.setServicePackage(pro.getProperty("servicePackage"));
            configBean.setTableName(pro.getProperty("tableName"));
            configBean.setTargetProject(pro.getProperty("targetProject"));
            configBean.setUserId(pro.getProperty("userId"));
            //configBean.setModelPackage(pro.getProperty("modelPackage"));
            //configBean.setSqlMapperPackage(pro.getProperty("sqlMapperPackage"));
            //configBean.setDaoMapperPackage(pro.getProperty("daoMapperPackage"));
            configBean.setBasePackage(pro.getProperty("basePackage"));
            configBean.setModuleName(pro.getProperty("moduleName"));

            //路径
            String javaResource = "\\src\\main\\java\\"+configBean.getBasePackage().replaceAll("\\.","\\\\");
            String xmlResource =  "\\src\\main\\resources\\mybatis";
            configBean.setJavaBeanLocation(configBean.getTargetProject() + pro.getProperty("parentModule") + "\\" + pro.getProperty("parentModule") + "-" + pro.getProperty("beanModule") + javaResource);
            configBean.setJavaDaoLocation(configBean.getTargetProject() +pro.getProperty("parentModule") + "\\" + pro.getProperty("parentModule") + "-" + pro.getProperty("daoModule") + javaResource);
            configBean.setJavaMapperXmlLocation(configBean.getTargetProject() +pro.getProperty("parentModule") + "\\" + pro.getProperty("parentModule") + "-" + pro.getProperty("daoModule") + xmlResource);
            configBean.setJavaServiceLocation(configBean.getTargetProject() +pro.getProperty("parentModule") + "\\" + pro.getProperty("parentModule") + "-" + pro.getProperty("serviceModule") + javaResource);
            configBean.setJavaControllerLocation(configBean.getTargetProject() +pro.getProperty("parentModule") + "\\" + pro.getProperty("parentModule") + "-" + pro.getProperty("controllerModule") + javaResource);

            configBean.setfTargetProject(pro.getProperty("fTargetProject"));
            configBean.setfEjsPrefix(pro.getProperty("fEjsPrefix"));
            configBean.setfShowColumn(pro.getProperty("fShowColumn"));
            configBean.setIsFront(pro.getProperty("isFront"));
            configBean.setIsDao(pro.getProperty("isDao"));
            configBean.setIsModel(pro.getProperty("isModel"));

            TableBean tableBean = new TableBean();
            tableBean.setTableName(pro.getProperty("tableName"));
            tableBean.setDbName(pro.getProperty("dbName"));
            tableBean.setTableColumes(getColumnBeans(tableBean));

            configBean.setTable(tableBean);

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configBean;
    }

    /**
     * 获取表字段信息列表
     * @param tableBean
     * @return
     */
    public static List<ColumnBean> getColumnBeans(TableBean tableBean){
        List<ColumnBean> ColumnBeans=new ArrayList<ColumnBean>();
        Connection conn = null;
        Statement smt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getInstance().getConnection();
            String sql="select t.COLUMN_NAME," +
                    "t.COLUMN_COMMENT," +
                    "t.COLUMN_DEFAULT," +
                    "t.DATA_TYPE," +
                    "t.COLUMN_KEY," +
                    "t.CHARACTER_MAXIMUM_LENGTH," +
                    "t.IS_NULLABLE " +
                    "from information_schema.`COLUMNS` t " +
                    "where t.TABLE_SCHEMA='"+tableBean.getDbName()+"' " +
                    "and " +
                    "t.TABLE_NAME='"+tableBean.getTableName()+"'";
            System.out.println(sql);
            smt = conn.createStatement();
            rs = smt.executeQuery(sql);
            while(rs.next()){
                ColumnBean ColumnBean=new ColumnBean();
                ColumnBean.setColumsNameDB(StrUtils.toLowerCase(rs.getString(1)));
                ColumnBean.setColumnNameCLASS(StrUtils.toLowerCaseFirstOne(StrUtils.toUpperCase(ColumnBean.getColumsNameDB(),"_")));
                ColumnBean.setColumsNote(StrUtils.replaceAll(rs.getString(2), "\n", ""));
                ColumnBean.setColumsValue(rs.getString(3));
                ColumnBean.setColumsType(rs.getString(4));
                ColumnBean.setColumsKey(rs.getString(5));
                ColumnBean.setColumsLength(rs.getString(6));
                ColumnBean.setColumsIsNull(rs.getString(7));
                ColumnBeans.add(ColumnBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                smt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ColumnBeans;
    }
}
