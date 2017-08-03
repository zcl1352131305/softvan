package cn.com.softvan.code;

import cn.com.softvan.Main;
import cn.com.softvan.code.bean.ColumnBean;
import cn.com.softvan.code.bean.ConfigBean;
import cn.com.softvan.utils.DateUtil;
import cn.com.softvan.utils.IOUtil;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class ModelCode extends BaseCode {

    public static void createModelFile(ConfigBean configBean){
        String fileUrl = configBean.getJavaBeanLocation()+"\\model\\"+configBean.getModulePackage().replaceAll("\\.","\\\\");
        MkDir(fileUrl);
        String className = configBean.getObjectName();
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+className+".java","utf8");
            pw.write(getModelCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getModelCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodeModel.txt";
        controllerFile = controllerFile.replaceAll("%20"," ");
        StringBuilder str = IOUtil.readFileByLines(controllerFile);
        String str1 = str.toString();
        //模块包名
        str1 = str1.replaceAll("ecModulePackage",configBean.getModulePackage());
        //生成日期
        str1 = str1.replaceAll("ecModuleDate", DateUtil.getNowDate());
        //模块名称
        str1 = str1.replaceAll("ecModuleObjectName",configBean.getObjectName());
        //基础包名
        str1 = str1.replaceAll("ecModuleBasePackage",configBean.getBasePackage());

        StringBuilder str2 = new StringBuilder();
        for (ColumnBean column: configBean.getTable().getTableColumes()) {
            if (isBaseColumn(column.getColumsNameDB())){
                continue;
            }else{
                str2.append("\t/**\n");
                str2.append("\t * @Fields userName "+column.getColumsNote()+"\n");
                str2.append("\t */\n");
                str2.append("\tprivate String "+column.getColumnNameCLASS()+";\n");
            }
        }
        str1 = str1.replaceAll("ecModuleProperty",str2.toString());

        return new StringBuilder(str1);
    }

}
