package cn.com.softvan.utils;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class AnnotationUtil {
    public static StringBuilder getClassAnno(String ClassName){
        StringBuilder str = new StringBuilder();
        str.append("/*\n");
        str.append(" * @ClassName "+ClassName+"\n");
        str.append(" * @Description \n");
        str.append(" * @version 1.0\n");
        str.append(" * @Date "+DateUtil.getNowDate()+"\n");
        str.append(" */\n");
        return str;
    }

    public static StringBuilder getControllerMethodAnno(String methodName ,String methodNameCH){
        StringBuilder str = new StringBuilder();
        str.append("\t/**\n");
        str.append("\t * 新增\n");
        str.append("\t * @param bean\n");
        str.append("\t * @return\n");
        str.append("\t */\n");
        return str;
    }
}
