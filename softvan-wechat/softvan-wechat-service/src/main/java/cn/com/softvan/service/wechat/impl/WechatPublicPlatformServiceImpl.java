/*
 * @ClassName WechatPublicPlatformServiceImpl
 * @Description
 * @version 1.0
 * @Date 2017/7/6 10:21:15
 */
package cn.com.softvan.service.wechat.impl;

import cn.com.softvan.dao.wechat.WechatPublicPlatformMapper;
import cn.com.softvan.model.wechat.WechatPublicPlatform;
import cn.com.softvan.service.wechat.IWechatPublicPlatformService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatPublicPlatformServiceImpl implements IWechatPublicPlatformService{
    @Autowired
    private WechatPublicPlatformMapper wechatPublicPlatformMapper;

    public int deleteById(WechatPublicPlatform bean) throws Exception{
        return wechatPublicPlatformMapper.deleteByPrimaryKey(bean.getId());
    }

    public int save(WechatPublicPlatform bean) throws Exception{
        WechatPublicPlatform bean1 = wechatPublicPlatformMapper.selectByPrimaryKey(bean.getId());
        if(null != bean1){
            return 1;
        }
        else{
            return wechatPublicPlatformMapper.insert(bean);
        }
    }

    public int update(WechatPublicPlatform bean) throws Exception{
        return wechatPublicPlatformMapper.updateByPrimaryKeySelective(bean);
    }

    public WechatPublicPlatform findDataById(WechatPublicPlatform bean){
        return wechatPublicPlatformMapper.selectByPrimaryKey(bean.getId());
    }

    public List<WechatPublicPlatform> findDataIsPage(WechatPublicPlatform bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return wechatPublicPlatformMapper.selectList(bean);
    }

    public List<WechatPublicPlatform> findDataIsList(WechatPublicPlatform bean) {
        return wechatPublicPlatformMapper.selectList(bean);
    }

}
