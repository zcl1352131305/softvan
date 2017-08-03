package cn.com.softvan.code;

import cn.com.softvan.Main;
import cn.com.softvan.code.bean.ColumnBean;
import cn.com.softvan.code.bean.ConfigBean;
import cn.com.softvan.utils.IOUtil;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class DaoMapperCode extends BaseCode{

    public static void createDaoMapperFile(ConfigBean configBean){
        String fileUrl = configBean.getJavaMapperXmlLocation();
        MkDir(fileUrl);
        String className = configBean.getObjectName()+"Mapper";
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+className+".xml","utf8");
            pw.write(getDaoMapperCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getDaoMapperCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodeDaoMapper.txt";
        controllerFile = controllerFile.replaceAll("%20"," ");
        StringBuilder str = IOUtil.readFileByLines(controllerFile);
        String str1 = str.toString();
        //模块包名
        str1 = str1.replaceAll("ecModulePackage",configBean.getModulePackage());
        //模块名称
        str1 = str1.replaceAll("ecModuleObjectName",configBean.getObjectName());
        //基础包名
        str1 = str1.replaceAll("ecModuleBasePackage",configBean.getBasePackage());
        //表名
        str1 = str1.replaceAll("ecModuleTable",configBean.getTableName());
        StringBuilder baseColumnList = new StringBuilder();
        StringBuilder insertTableColumn = new StringBuilder();
        StringBuilder insertTableParam = new StringBuilder();
        StringBuilder updateSelective = new StringBuilder();
        for (ColumnBean column: configBean.getTable().getTableColumes()) {
            if (isBaseColumn(column.getColumsNameDB())){
                continue;
            }else{
                baseColumnList.append("\t\tt1."+column.getColumsNameDB()+" as "+column.getColumnNameCLASS()+",\n");
                insertTableColumn.append("\t\t\t"+column.getColumsNameDB()+",\n");
                insertTableParam.append("\t\t\t#{"+column.getColumnNameCLASS()+"},\n");
                updateSelective.append("\t\t<if test=\""+column.getColumnNameCLASS()+" != null\" >\n");
                updateSelective.append("\t\t\t"+column.getColumsNameDB()+" = #{"+column.getColumnNameCLASS()+"},\n");
                updateSelective.append("\t\t</if>\n");
            }
        }
        str1 = str1.replaceAll("ecModuleBaseColumnList",baseColumnList.toString());
        str1 = str1.replaceAll("ecModuleInsertTableColumn",insertTableColumn.toString());
        str1 = str1.replaceAll("ecModuleInsertTableParam",insertTableParam.toString());
        str1 = str1.replaceAll("ecModuleUpdateSelective",updateSelective.toString());
        return new StringBuilder(str1);
    }

}
