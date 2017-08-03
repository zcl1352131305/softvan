package cn.com.softvan.code;

import cn.com.softvan.Main;
import cn.com.softvan.code.bean.ConfigBean;
import cn.com.softvan.utils.IOUtil;
import cn.com.softvan.utils.StrUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class PageInitCode extends BaseCode {

    public static void createPageInitFile(ConfigBean configBean){
        String fileUrl = configBean.getfTargetProject()+"\\views\\"+configBean.getModulePackage().replaceAll("\\.","\\\\");
        MkDir(fileUrl);
        String fileName = configBean.getfEjsPrefix();
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+fileName+".ejs","utf8");
            pw.write(getPageInitCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getPageInitCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodePageInit.txt";
        controllerFile = controllerFile.replaceAll("%20"," ");
        StringBuilder str = IOUtil.readFileByLines(controllerFile);
        String str1 = str.toString();
        //模块中文名称
        str1 = str1.replaceAll("ecModuleName",configBean.getModuleName());
        //模块名称首字母小写
        str1 = str1.replaceAll("ecModuleObjectNameFirstLower", StrUtils.toLowerCaseFirstOne(configBean.getObjectName()));
        //中间链接匹配名
        String middleLink = configBean.getModulePackage().replaceAll("\\.","/");
        str1 = str1.replaceAll("ecModulepackageLink",middleLink);
        //回退目录
        String preLocation = "";
        for (int i = 0; i<configBean.getModulePackage().split("\\.").length;i++){
            preLocation += "../";
        }
        str1 = str1.replaceAll("ecModulePreLocation",preLocation);
        return new StringBuilder(str1);
    }

}
