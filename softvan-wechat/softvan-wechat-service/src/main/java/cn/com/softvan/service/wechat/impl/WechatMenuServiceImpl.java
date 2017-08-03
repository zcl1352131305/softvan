/*
 * @ClassName WechatMenuServiceImpl
 * @Description
 * @version 1.0
 * @Date 2017/7/6 4:57:5
 */
package cn.com.softvan.service.wechat.impl;

import cn.com.softvan.dao.wechat.WechatMenuMapper;
import cn.com.softvan.model.wechat.WechatMenu;
import cn.com.softvan.service.wechat.IWechatMenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatMenuServiceImpl implements IWechatMenuService {
    @Autowired
    private WechatMenuMapper wechatMenuMapper;

    public int deleteById(WechatMenu bean) throws Exception{
        return wechatMenuMapper.deleteByPrimaryKey(bean.getId());
    }

    public int save(WechatMenu bean) throws Exception{
        WechatMenu bean1 = wechatMenuMapper.selectByPrimaryKey(bean.getId());
        if(null != bean1){
            return 1;
        }
        else{
            return wechatMenuMapper.insert(bean);
        }
    }

    public int update(WechatMenu bean) throws Exception{
        return wechatMenuMapper.updateByPrimaryKeySelective(bean);
    }

    public WechatMenu findDataById(WechatMenu bean){
        return wechatMenuMapper.selectByPrimaryKey(bean.getId());
    }

    public List<WechatMenu> findDataIsPage(WechatMenu bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return wechatMenuMapper.selectList(bean);
    }

    public List<WechatMenu> findDataIsList(WechatMenu bean) {
        return wechatMenuMapper.selectList(bean);
    }

}
