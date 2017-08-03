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
public class PageEditCode extends BaseCode{

    public static void createPageEditFile(ConfigBean configBean){
        String fileUrl = configBean.getfTargetProject()+"\\views\\"+configBean.getModulePackage().replaceAll("\\.","\\\\");
        MkDir(fileUrl);
        String fileName = configBean.getfEjsPrefix();
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+fileName+"_edit.ejs","utf8");
            pw.write(getPageEditCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getPageEditCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodePageEdit.txt";
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

        //表单元素
        StringBuilder str2 = new StringBuilder();
        //验证前段
        StringBuilder vstr1 = new StringBuilder();
        //验证后段
        StringBuilder vstr2 = new StringBuilder();
        for (ColumnBean column: configBean.getTable().getTableColumes()) {
            if (isBaseColumn(column.getColumsNameDB())){
                continue;
            }
            else{

                str2.append("\t\t\t\t\t\t\t<div class=\"form-group\">\n");
                str2.append("\t\t\t\t\t\t\t\t<label class=\"col-sm-2 control-label\">"+column.getColumsNote()+"</label>\n");
                str2.append("\t\t\t\t\t\t\t\t<div class=\"col-sm-10\">\n");
                if(column.getColumsType().equals("number")){
                    str2.append("\t\t\t\t\t\t\t\t\t<input type=\"number\" name=\""+column.getColumnNameCLASS()+"\" class=\"form-control\" value=\"<%=data."+column.getColumnNameCLASS()+"%>\" >\n");
                    vstr1.append("\t\t\t\t\t"+column.getColumnNameCLASS()+": {\n");
                    vstr1.append("\t\t\t\t\t\tmaxlength:"+column.getColumsLength()+",\n");
                    vstr2.append("\t\t\t\t\t"+column.getColumnNameCLASS()+": {\n");
                    vstr2.append("\t\t\t\t\t\tmaxlength: icon + \"必须"+column.getColumsLength()+"字符以下\",\n");
                    vstr1.append("\t\t\t\t\t\trequire:true,\n");
                    vstr2.append("\t\t\t\t\t\trequire:\"不能为空\",\n");
                    vstr1.append("\t\t\t\t\t},\n");
                    vstr2.append("\t\t\t\t\t},\n");
                }
                else if(column.getColumsNameDB().indexOf("date") != -1 || column.getColumsType().equals("datetime")){
                    str2.append("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\""+column.getColumnNameCLASS()+"\" class=\"form-control\" value=\"<%=data."+column.getColumnNameCLASS()+"%>\" onclick=\"laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})\" >\n");
                }
                else{
                    vstr1.append("\t\t\t\t\t"+column.getColumnNameCLASS()+": {\n");
                    vstr1.append("\t\t\t\t\t\tmaxlength:"+column.getColumsLength()+",\n");
                    vstr2.append("\t\t\t\t\t"+column.getColumnNameCLASS()+": {\n");
                    vstr2.append("\t\t\t\t\t\tmaxlength: icon + \"必须"+column.getColumsLength()+"字符以下\",\n");
                    if("1".equals(column.getColumsIsNull())){
                        vstr1.append("\t\t\t\t\t\trequire:true,\n");
                        vstr2.append("\t\t\t\t\t\trequire:\"不能为空\",\n");
                    }
                    vstr1.append("\t\t\t\t\t},\n");
                    vstr2.append("\t\t\t\t\t},\n");


                    str2.append("\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\""+column.getColumnNameCLASS()+"\" class=\"form-control\" value=\"<%=data."+column.getColumnNameCLASS()+"%>\" >\n");
                }
                str2.append("\t\t\t\t\t\t\t\t</div>\n");
                str2.append("\t\t\t\t\t\t\t</div>\n");
                str2.append("\t\t\t\t\t\t\t<div class=\"hr-line-dashed\"></div>\n");


                //
            }
        }
        StringBuilder vstr = new StringBuilder();
        vstr.append("\t\t\t\trules: {\n");
        vstr.append(vstr1);
        vstr.append("\t\t\t\t},\n");
        vstr.append("\t\t\t\tmessages: {\n");
        vstr.append(vstr2);
        vstr.append("\t\t\t\t},\n");
        str1 = str1.replaceAll("ecModuleEditElement",str2.toString());
        str1 = str1.replaceAll("ecModuleValidate",vstr.toString());



        return new StringBuilder(str1);
    }

}
