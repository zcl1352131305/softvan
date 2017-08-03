package cn.com.softvan.code;

import cn.com.softvan.Main;
import cn.com.softvan.code.bean.ColumnBean;
import cn.com.softvan.code.bean.ConfigBean;
import cn.com.softvan.utils.IOUtil;
import cn.com.softvan.utils.StrUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class PageListCode extends BaseCode{

    public static void createPageListFile(ConfigBean configBean){
        String fileUrl = configBean.getfTargetProject()+"\\views\\"+configBean.getModulePackage().replaceAll("\\.","\\\\");
        MkDir(fileUrl);
        String fileName = configBean.getfEjsPrefix();
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+fileName+"_list.ejs","utf8");
            pw.write(getPageListCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getPageListCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodePageList.txt";
        controllerFile = controllerFile.replaceAll("%20"," ");
        StringBuilder str = IOUtil.readFileByLines(controllerFile);
        String str1 = str.toString();
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

        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        for (ColumnBean column: configBean.getTable().getTableColumes()) {
            if(configBean.getfShowColumn().indexOf(column.getColumsNameDB()) != -1){
                str2 .append("\t\t\t<th>"+column.getColumsNote()+"</th>\n");
                str3.append("\t\t\t<td><%=data."+column.getColumnNameCLASS()+"%></td>\n");
            }
        }
        str1 = str1.replaceAll("ecModuleColumnName",str2.toString());
        str1 = str1.replaceAll("ecModuleColumnValue",str3.toString());

        return new StringBuilder(str1);
    }

}
