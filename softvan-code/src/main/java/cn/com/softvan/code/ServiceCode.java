package cn.com.softvan.code;

import cn.com.softvan.Main;
import cn.com.softvan.code.bean.ConfigBean;
import cn.com.softvan.utils.DateUtil;
import cn.com.softvan.utils.IOUtil;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class ServiceCode extends BaseCode{

    public static void createServiceFile(ConfigBean configBean){
        String fileUrl = configBean.getJavaServiceLocation()+"\\service\\"+configBean.getModulePackage().replaceAll("\\.","\\\\");
        MkDir(fileUrl);
        String className = "I"+configBean.getObjectName()+"Service";
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+className+".java","utf8");
            pw.write(getServiceCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getServiceCode(ConfigBean configBean){
        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodeService.txt";
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
        return new StringBuilder(str1);
    }




    /*public static StringBuilder getServiceCode1(ConfigBean configBean){

        StringBuilder str = new StringBuilder();

        str.append(AnnotationUtil.getClassAnno("I"+configBean.getObjectName()+"Service"));

        str.append("package "+configBean.getServicePackage()+configBean.getModulePackage()+";\n");
        str.append("\n");
        str.append("import "+configBean.getModelPackage()+configBean.getModulePackage()+"."+configBean.getObjectName()+";\n");
        str.append("import "+configBean.getServicePackage()+".IBaseService;\n");
        str.append("\n");
        str.append("public interface I"+configBean.getObjectName()+"Service extends IBaseService<"+configBean.getObjectName()+"> {\n");
        str.append("\n}");

        return str;
    }*/
}
