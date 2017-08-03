/*
 * @ClassName CommonFileServiceImpl
 * @Description
 * @version 1.0
 * @Date 2017/7/11 11:39:2
 */
package cn.com.softvan.service.file.impl;

import cn.com.softvan.dao.file.CommonFileMapper;
import cn.com.softvan.model.file.CommonFile;
import cn.com.softvan.service.file.ICommonFileService;
import cn.com.softvan.utils.IdUtil;
import cn.com.softvan.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonFileServiceImpl implements ICommonFileService {
    @Autowired
    private CommonFileMapper commonFileMapper;

    public void saveFiles(String linkId, String key, List<CommonFile> files) throws Exception{
        if(null != files && files.size() > 0){
            CommonFile file1 = new CommonFile();
            file1.setLinkId(linkId);
            file1.setKeyword(key);
            commonFileMapper.deleteByLinkInfo(file1);
            for (CommonFile commonFile:files) {
                if(Validator.isEmpty(commonFile.getId())){
                    commonFile.setId(IdUtil.createUUID(32));
                }
                commonFile.setLinkId(linkId);
                commonFile.setKeyword(key);
                commonFileMapper.insert(commonFile);
            }
        }
    }

    public void saveFile(String linkId, String key, List<CommonFile> files) throws Exception{
        if(null != files && files .size() > 0){
            CommonFile commonFile = files.get(files.size()-1);
            commonFile.setLinkId(linkId);
            commonFile.setKeyword(key);
            commonFileMapper.deleteByLinkInfo(commonFile);
            if(Validator.isEmpty(commonFile.getId())){
                commonFile.setId(IdUtil.createUUID(32));
            }
            commonFileMapper.insert(commonFile);
        }
    }

    public void saveFile(String linkId, String key, CommonFile commonFile) throws Exception{
        if(null != commonFile){
            commonFile.setLinkId(linkId);
            commonFile.setKeyword(key);
            commonFileMapper.deleteByLinkInfo(commonFile);
            if(Validator.isEmpty(commonFile.getId())){
                commonFile.setId(IdUtil.createUUID(32));
            }
            commonFileMapper.insert(commonFile);
        }
    }

    /*@Override
    public CommonFile getFile(String linkId, String keyWord) {
        CommonFile commonFile = new CommonFile();
        List<CommonFile> files = getFiles(linkId,keyWord);
        if(null != files && files.size() > 0){
            commonFile = files.get(0);
        }
        return commonFile;
    }*/

    @Override
    public List<CommonFile> getFiles(String linkId, String keyWord) {
        CommonFile commonFile = new CommonFile();
        commonFile.setLinkId(linkId);
        commonFile.setKeyword(keyWord);
        List<CommonFile> files = commonFileMapper.selectByLinkInfo(commonFile);
        if(null != files && files.size() > 0){
            return files;
        }
        return null;
    }
}
