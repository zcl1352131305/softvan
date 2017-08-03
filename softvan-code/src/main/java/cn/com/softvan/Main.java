package cn.com.softvan;

import cn.com.softvan.code.*;
import cn.com.softvan.code.bean.ConfigBean;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class Main {
    public static void main(String args[]){
        String fileurl=Main.class.getResource("/").getPath()+"generatorConfig.properties";
        ConfigBean configBean = CodeConfigInit.getConfig(fileurl);

        if(configBean.getIsModel().equals("1")){
            ModelCode.createModelFile(configBean);
        }
        if(configBean.getIsDao().equals("1")){
            DaoCode.createDaoFile(configBean);
            DaoMapperCode.createDaoMapperFile(configBean);
        }
        if(configBean.getIsController().equals("1")){
            ControllerCode.createControllerFile(configBean);
        }
        if(configBean.getIsService().equals("1")){
            ServiceCode.createServiceFile(configBean);
            ServiceImplCode.createServiceImplFile(configBean);
        }
        if(configBean.getIsFront().equals("1")){
            NodeJsCode.createNodeJsFile(configBean);
            PageInitCode.createPageInitFile(configBean);
            PageEditCode.createPageEditFile(configBean);
            PageListCode.createPageListFile(configBean);
        }
    }
}
