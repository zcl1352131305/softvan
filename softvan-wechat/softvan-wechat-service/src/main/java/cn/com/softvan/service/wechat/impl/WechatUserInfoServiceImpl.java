/*
 * @ClassName WechatUserInfoServiceImpl
 * @Description
 * @version 1.0
 * @Date 2017/7/6 3:37:29
 */
package cn.com.softvan.service.wechat.impl;

import cn.com.softvan.dao.wechat.WechatUserInfoMapper;
import cn.com.softvan.model.wechat.WechatUserInfo;
import cn.com.softvan.service.wechat.IWechatUserInfoService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatUserInfoServiceImpl implements IWechatUserInfoService {
    @Autowired
    private WechatUserInfoMapper wechatUserInfoMapper;

    public int deleteById(WechatUserInfo bean) throws Exception{
        return wechatUserInfoMapper.deleteByPrimaryKey(bean.getId());
    }

    public int save(WechatUserInfo bean) throws Exception{
        WechatUserInfo bean1 = wechatUserInfoMapper.selectByOpenId(bean.getOpenId());
        if(null != bean1){
            //微信用户已存在，更新用户信息
            bean.setId(bean1.getId());
            return wechatUserInfoMapper.updateByPrimaryKeySelective(bean);
        }
        else{
            //否则插入新用户
            return wechatUserInfoMapper.insert(bean);
        }
    }

    public int update(WechatUserInfo bean) throws Exception{
        return wechatUserInfoMapper.updateByPrimaryKeySelective(bean);
    }

    public WechatUserInfo findDataById(WechatUserInfo bean){
        return wechatUserInfoMapper.selectByPrimaryKey(bean.getId());
    }

    public List<WechatUserInfo> findDataIsPage(WechatUserInfo bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return wechatUserInfoMapper.selectList(bean);
    }

    public List<WechatUserInfo> findDataIsList(WechatUserInfo bean) {
        return wechatUserInfoMapper.selectList(bean);
    }

    @Override
    public WechatUserInfo selectByOpenId(String openId) {
        return wechatUserInfoMapper.selectByOpenId(openId);
    }
}
