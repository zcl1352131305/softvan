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
public class ServiceImplCode extends BaseCode{

    public static void createServiceImplFile(ConfigBean configBean){
        String fileUrl = configBean.getJavaServiceLocation()+"\\service\\"+configBean.getModulePackage().replaceAll("\\.","\\\\")+"\\impl";
        MkDir(fileUrl);
        String className = configBean.getObjectName()+"ServiceImpl";
        try {
            PrintWriter pw= null;
            pw = new PrintWriter(fileUrl+"/"+className+".java","utf8");
            pw.write(getServiceImplCode(configBean).toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private static StringBuilder getServiceImplCode(ConfigBean configBean){

        String controllerFile=Main.class.getResource("/").getPath()+"template\\EasycodeServiceImpl.txt";
        controllerFile = controllerFile.replaceAll("%20"," ");
        StringBuilder str = IOUtil.readFileByLines(controllerFile);
        String str1 = str.toString();
        //模块名称首字母小写
        str1 = str1.replaceAll("ecModuleObjectNameFirstLower",StrUtils.toLowerCaseFirstOne(configBean.getObjectName()));
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


    /*public static StringBuilder getServiceImplCode1(ConfigBean configBean){

        String objName = configBean.getObjectName();
        String mapperName = StrUtils.toLowerCaseFirstOne(objName)+"Mapper";

        StringBuilder str = new StringBuilder();

        str.append(AnnotationUtil.getClassAnno(objName+"ServiceImpl"));

        str.append("package "+configBean.getServicePackage()+configBean.getModulePackage()+".impl;\n");
        str.append("\n");
        str.append("import "+configBean.getDaoMapperPackage()+configBean.getModulePackage()+"."+objName+"Mapper;\n");
        str.append("import "+configBean.getModelPackage()+".BaseBean;\n");
        str.append("import "+configBean.getModelPackage()+configBean.getModulePackage()+"."+objName+";\n");
        str.append("import "+configBean.getServicePackage()+configBean.getModulePackage()+".I"+objName+"Service;\n");
        str.append("import com.github.pagehelper.PageHelper;\n");
        str.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        str.append("import org.springframework.stereotype.Service;\n");
        str.append("import java.util.List;\n");
        str.append("\n");

        str.append("@Service\n");
        str.append("public class "+objName+"ServiceImpl implements I"+objName+"Service {\n");
        str.append("\t@Autowired\n");
        str.append("\tprivate "+objName+"Mapper "+mapperName+";\n");
        str.append("\n");

        str.append("\tpublic "+objName+" findDataById(BaseBean bean) {\n");
        str.append("\t\treturn ("+objName+") "+mapperName+".selectByPrimaryKey(bean.getId());\n");
        str.append("\t}\n");
        str.append("\n");

        str.append("\tpublic int deleteById(BaseBean bean) throws Exception{\n");
        str.append("\t\treturn "+mapperName+".deleteByPrimaryKey(bean.getId());\n");
        str.append("\t}\n");
        str.append("\n");

        str.append("\tpublic int save(BaseBean bean) throws Exception{\n");
        str.append("\t\treturn "+mapperName+".insert(bean);\n");
        str.append("\t}\n");
        str.append("\n");

        str.append("\tpublic int update(BaseBean bean) throws Exception{\n");
        str.append("\t\treturn "+mapperName+".updateByPrimaryKeySelective(bean);\n");
        str.append("\t}\n");
        str.append("\n");

        str.append("\tpublic List<"+objName+"> findDataIsPage(BaseBean bean) {\n");
        str.append("\t\tif(bean.getPage() != null && bean.getRows() != null){\n");
        str.append("\t\t\tPageHelper.startPage(bean.getPage(),bean.getRows());\n");
        str.append("\t\t}\n");
        str.append("\t\treturn (List<"+objName+">) "+mapperName+".selectList(bean);\n");
        str.append("\t}\n");
        str.append("\n");

        str.append("\tpublic List<"+objName+"> findDataIsList(BaseBean bean){\n");
        str.append("\t\treturn (List<"+objName+">) "+mapperName+".selectList(bean);\n");
        str.append("\t}\n");
        str.append("}");

        return str;
    }*/
}
