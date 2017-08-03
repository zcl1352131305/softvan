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
 * Created by Administrator on 2017/6/23 0023.
 */
public class ControllerCode extends BaseCode {

    public static void createControllerFile(ConfigBean configBean){
        String fileUrl = configBean.getJavaControllerLocation()+"\\api\\"+configBean.getModulePackage().replaceAll("\\.","\\\\");
        MkDir(fileUrl);
        String className = configBean.getObjectName()+"Controller";
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+className+".java","utf8");
            pw.write(getControllerCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder getControllerCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodeController.txt";
        controllerFile = controllerFile.replaceAll("%20"," ");
        StringBuilder str = IOUtil.readFileByLines(controllerFile);
        String str1 = str.toString();
        //模块名称首字母小写
        str1 = str1.replaceAll("ecModuleObjectNameFirstLower",StrUtils.toLowerCaseFirstOne(configBean.getObjectName()));
        //模块包名
        str1 = str1.replaceAll("ecModulePackage",configBean.getModulePackage());
        //生成日期
        str1 = str1.replaceAll("ecModuleDate", DateUtil.getNowDate());
        //中间链接匹配名
        String middleLink = configBean.getModulePackage().replaceAll("\\.","/");
        str1 = str1.replaceAll("ecModulepackageLink",middleLink);
        //模块名称
        str1 = str1.replaceAll("ecModuleObjectName",configBean.getObjectName());
        //基础包名
        str1 = str1.replaceAll("ecModuleBasePackage",configBean.getBasePackage());
        return new StringBuilder(str1);
    }


    /*public static StringBuilder getControllerCode1(ConfigBean configBean){

        String objName = configBean.getObjectName();
        String serviceName = StrUtils.toLowerCaseFirstOne(objName)+"Service";

        StringBuilder str = new StringBuilder();

        str.append(AnnotationUtil.getClassAnno(configBean.getObjectName()+"Controller"));

        str.append("package "+configBean.getControllerPackage()+configBean.getModulePackage()+";\n");
        str.append("\n");
        str.append("import "+configBean.getBasePackage()+".common.IdUtil;\n");
        str.append("import "+configBean.getBasePackage()+".common.Result;\n");
        str.append("import "+configBean.getBasePackage()+".common.Validator;\n");
        str.append("import "+configBean.getControllerPackage()+".BaseController;\n");
        str.append("import "+configBean.getModelPackage()+configBean.getModulePackage()+"."+configBean.getObjectName()+";\n");
        str.append("import "+configBean.getServicePackage()+configBean.getModulePackage()+".I"+objName+"Service;\n");
        str.append("import com.github.pagehelper.PageInfo;\n");
        str.append("import lombok.extern.slf4j.Slf4j;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.security.access.prepost.PreAuthorize;\n");
        str.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
        str.append("import org.springframework.web.bind.annotation.RequestMethod;\n");
        str.append("import org.springframework.web.bind.annotation.ResponseBody;\n");
        str.append("import org.springframework.web.bind.annotation.RestController;\n");
        str.append("import java.util.List;\n");
        str.append("\n");

        str.append("@RestController\n");
        str.append("@RequestMapping(value = \"/h"+configBean.getModulePackage().replaceAll("\\.","/")+"/"+ StrUtils.toLowerCaseFirstOne(objName)+"\")\n");
        str.append("@Slf4j\n");
        str.append("@ResponseBody\n");
        str.append("public class "+objName+"Controller extends BaseController {\n");
        str.append("\t@Autowired\n");
        str.append("\tprivate I"+objName+"Service "+serviceName+";\n");
        str.append("\n");

        //新增方法
        str.append(AnnotationUtil.getControllerMethodAnno("add","新增"));
        str.append("\t@RequestMapping(value = \"/add\",method= RequestMethod.POST)\n");
        str.append("\tpublic Result add("+objName+" bean){\n");
        str.append("\t\tlog.info(this.getClass().getName()+\"======================add\");\n");
        str.append("\t\tif(null != bean){\t\n");
        str.append("\t\t\tif(Validator.isEmpty(bean.getId())){\n");
        str.append("\t\t\t\tbean.setId(IdUtil.createUUID(32));\n");
        str.append("\t\t\t}\n");
        str.append("\t\t\ttry {\n");
        str.append("\t\t\t\t"+serviceName+".save(bean);\n");
        str.append("\t\t\t\treturn Result.success(\"添加成功！\");\n");
        str.append("\t\t\t} catch (Exception e) {\n");
        str.append("\t\t\t\te.printStackTrace();\n");
        str.append("\t\t\t\treturn Result.sysError(e);\n");
        str.append("\t\t\t}\n");
        str.append("\t\t}\n");
        str.append("\t\telse{\n");
        str.append("\t\t\treturn Result.paramError();\n");
        str.append("\t\t}\n");
        str.append("\t}\n");

        //新增方法
        str.append(AnnotationUtil.getControllerMethodAnno("add","新增"));
        str.append("\t@RequestMapping(value = \"/add\",method= RequestMethod.POST)\n");
        str.append("\tpublic Result add("+objName+" bean){\n");
        str.append("\t\tlog.info(this.getClass().getName()+\"======================add\");\n");
        str.append("\t\tif(null != bean){\t\n");
        str.append("\t\t\tif(Validator.isEmpty(bean.getId())){\n");
        str.append("\t\t\t\tbean.setId(IdUtil.createUUID(32));\n");
        str.append("\t\t\t}\n");
        str.append("\t\t\ttry {\n");
        str.append("\t\t\t\t"+serviceName+".save(bean);\n");
        str.append("\t\t\t\treturn Result.success(\"添加成功！\");\n");
        str.append("\t\t\t} catch (Exception e) {\n");
        str.append("\t\t\t\te.printStackTrace();\n");
        str.append("\t\t\t\treturn Result.sysError(e);\n");
        str.append("\t\t\t}\n");
        str.append("\t\t}\n");
        str.append("\t\telse{\n");
        str.append("\t\t\treturn Result.paramError();\n");
        str.append("\t\t}\n");
        str.append("\t}\n");


        str.append("}");

        return str;
    }*/
}
