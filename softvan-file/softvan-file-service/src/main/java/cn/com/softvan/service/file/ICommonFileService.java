/*
 * @ClassName ICommonFileService
 * @Description
 * @version 1.0
 * @Date 2017/7/11 11:39:2
 */
package cn.com.softvan.service.file;

import cn.com.softvan.model.file.CommonFile;

import java.util.List;

public interface ICommonFileService{
    void saveFile(String linkId, String keyword, CommonFile CommonFile) throws Exception;

    void saveFile(String linkId, String keyword, List<CommonFile> files) throws Exception;

    void saveFiles(String linkId, String keyword, List<CommonFile> files) throws Exception;

    /*public CommonFile getFile(String linkId, String keyWord);*/

    List<CommonFile> getFiles(String linkId, String keyWord);
}
