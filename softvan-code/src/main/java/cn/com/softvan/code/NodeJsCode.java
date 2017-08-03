package cn.com.softvan.code;

import cn.com.softvan.Main;
import cn.com.softvan.code.bean.ConfigBean;
import cn.com.softvan.utils.DateUtil;
import cn.com.softvan.utils.IOUtil;
import cn.com.softvan.utils.StrUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class NodeJsCode extends BaseCode{

    public static void createNodeJsFile(ConfigBean configBean){
        String fileUrl = configBean.getfTargetProject()+"\\routes\\"+configBean.getModulePackage().replaceAll("\\.","\\\\");
        MkDir(fileUrl);
        String fileName = StrUtils.toLowerCaseFirstOne(configBean.getObjectName());
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+fileName+".js","utf8");
            pw.write(getNodeJsCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getNodeJsCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodeNodeJs.txt";
        controllerFile = controllerFile.replaceAll("%20"," ");
        StringBuilder str = IOUtil.readFileByLines(controllerFile);
        String str1 = str.toString();
        //模块名称首字母小写
        str1 = str1.replaceAll("ecModuleObjectNameFirstLower", StrUtils.toLowerCaseFirstOne(configBean.getObjectName()));
        //生成日期
        str1 = str1.replaceAll("ecModuleDate", DateUtil.getNowDate());
        //中间链接匹配名
        String middleLink = configBean.getModulePackage().replaceAll("\\.","/");
        str1 = str1.replaceAll("ecModulepackageLink",middleLink);

        str1 = str1.replaceAll("ecModuleEjsPrefix",configBean.getfEjsPrefix());

        String preLocation = "../";
        for (int i = 0; i<configBean.getModulePackage().split("\\.").length;i++){
            preLocation += "../";
        }
        str1 = str1.replaceAll("ecModulePreLocation",preLocation);
        return new StringBuilder(str1);
    }
}
