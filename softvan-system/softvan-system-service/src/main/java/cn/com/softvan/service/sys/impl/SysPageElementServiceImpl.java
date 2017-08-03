/*
 * @ClassName SysPageElementServiceImpl
 * @Description
 * @version 1.0
 * @Date 2017/6/30 4:59:11
 */
package cn.com.softvan.service.sys.impl;

import cn.com.softvan.dao.sys.SysPageElementMapper;
import cn.com.softvan.dao.sys.SysPermitMapper;
import cn.com.softvan.dao.sys.SysRolePageElementMapper;
import cn.com.softvan.model.sys.SysMenu;
import cn.com.softvan.model.sys.SysPageElement;
import cn.com.softvan.model.sys.SysPermit;
import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.service.sys.ISysPageElementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@EnableTransactionManagement
public class SysPageElementServiceImpl implements ISysPageElementService {
    @Autowired
    private SysPageElementMapper sysPageElementMapper;
    @Autowired
    private SysPermitMapper sysPermitMapper;
    @Autowired
    private SysRolePageElementMapper sysRolePageElementMapper;

    @Transactional(rollbackFor=Exception.class)
    public int deleteById(SysPageElement bean) throws Exception{
        sysPermitMapper.deletePermitByElementId(bean.getId());
        return sysPageElementMapper.deleteByPrimaryKey(bean.getId());
    }

    @Transactional(rollbackFor=Exception.class)
    public int save(SysPageElement bean) throws Exception{
        int rs = sysPageElementMapper.insert(bean);
        if(null != bean.getPermits()){
            for (SysPermit permit:bean.getPermits()) {
                sysPermitMapper.insert(permit);
            }
        }
        return rs;
    }

    @Transactional(rollbackFor=Exception.class)
    public int update(SysPageElement bean) throws Exception{
        if(null != bean.getPermits()){
            sysPermitMapper.deletePermitByElementId(bean.getId());
            for (SysPermit permit:bean.getPermits()) {
                sysPermitMapper.insert(permit);
            }
        }
        return sysPageElementMapper.updateByPrimaryKeySelective(bean);
    }

    public SysPageElement findDataById(SysPageElement bean){
        return (SysPageElement)sysPageElementMapper.selectByPrimaryKey(bean.getId());
    }

    public List<SysPageElement> findDataIsPage(SysPageElement bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return (List<SysPageElement>) sysPageElementMapper.selectList(bean);
    }

    public List<SysPageElement> findDataIsList(SysPageElement bean) {
        return (List<SysPageElement>) sysPageElementMapper.selectList(bean);
    }


    /**
     * 根据角色id查询所有菜单
     * TODO 增加缓存
     * @return
     */
    public List<SysMenu> findPageElementsByRoleId(SysUser user){
        //查询所有
        List<SysPageElement> all = sysPageElementMapper.selectList(new SysPageElement());
        //现有
        List<SysPageElement> now = sysPageElementMapper.selectUserMenu(user);
        //生成Map
        Map<String, SysMenu> allM = new HashMap<>();
        for (SysPageElement t : all) {
            SysMenu temp = new SysMenu(t.getId(), t.getParent(), t.getTitle(), t.getIcon(),t.getUrl(), t.getType(), t.getSort());
            allM.put(t.getId(), temp);
        }
        List<SysMenu> menus = new ArrayList<>();
        for (SysPageElement t : now) {
            SysMenu n = allM.get(t.getId());
            if(!"1".equals(n.getParent())){
                SysMenu parent = allM.get(n.getParent());
                parent.getChild().add(n);
                while (!"1".equals(parent.getParent())) {
                    SysMenu temp = allM.get(parent.getParent());
                    temp.getChild().add(parent);
                    parent = temp;
                }
                //此時temp為頂級
                boolean isContain = false;
                for (SysMenu topElement : menus) {
                    if (topElement.getId().equals(parent.getId())) {
                        isContain = true;
                        break;
                    }
                }
                //如果没包含
                if (!isContain) {
                    menus.add(parent);
                }
            }else{
                menus.add(n);
            }
        }
        return menus;
    }

}
