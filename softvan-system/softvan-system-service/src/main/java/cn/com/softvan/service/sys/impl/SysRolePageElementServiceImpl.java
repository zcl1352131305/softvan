/*
 * @ClassName SysRolePageElementServiceImpl
 * @Description
 * @version 1.0
 * @Date 2017/7/3 4:19:35
 */
package cn.com.softvan.service.sys.impl;

import cn.com.softvan.dao.sys.SysRolePageElementMapper;
import cn.com.softvan.model.sys.SysRolePageElement;
import cn.com.softvan.service.sys.ISysRolePageElementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRolePageElementServiceImpl implements ISysRolePageElementService {
    @Autowired
    private SysRolePageElementMapper sysRolePageElementMapper;

    public int deleteById(SysRolePageElement bean) throws Exception{
        return sysRolePageElementMapper.deleteByPrimaryKey(bean.getId());
    }

    public int save(SysRolePageElement bean) throws Exception{
        return sysRolePageElementMapper.insert(bean);
    }

    public int update(SysRolePageElement bean) throws Exception{
        return sysRolePageElementMapper.updateByPrimaryKeySelective(bean);
    }

    public SysRolePageElement findDataById(SysRolePageElement bean){
        return (SysRolePageElement)sysRolePageElementMapper.selectByPrimaryKey(bean.getId());
    }

    public List<SysRolePageElement> findDataIsPage(SysRolePageElement bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return (List<SysRolePageElement>) sysRolePageElementMapper.selectList(bean);
    }

    public List<SysRolePageElement> findDataIsList(SysRolePageElement bean) {
        return (List<SysRolePageElement>) sysRolePageElementMapper.selectList(bean);
    }

    public int deleteByRoleId(String roleId){
        return sysRolePageElementMapper.deleteByRoleId(roleId);
    }

}
