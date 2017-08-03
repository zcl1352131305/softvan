package cn.com.softvan.code;

import java.io.File;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class BaseCode {
    /**
     * 生成文件夹
     * @param fileurl
     */
    protected static void MkDir(String fileurl){
        //创建文件夹
        File saveDirFile = new File(fileurl);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
    }

    protected static boolean isBaseColumn(String name){
        boolean flag = false;
        switch (name){
            case "id":flag = true;break;
            case "date_created":flag = true;break;
            case "create_id":flag = true;break;
            case "create_ip":flag = true;break;
            case "date_updated":flag = true;break;
            case "update_id":flag = true;break;
            case "update_ip":flag = true;break;
            case "version":flag = true;break;
            case "del_flag":flag = true;break;
        }

        return flag;
    }

}
