/*
 * @ClassName SysPermitServiceImpl
 * @Description
 * @version 1.0
 * @Date 2017/6/30 5:54:20
 */
package cn.com.softvan.service.sys.impl;

import cn.com.softvan.dao.sys.SysPermitMapper;
import cn.com.softvan.model.sys.SysPermit;
import cn.com.softvan.service.sys.ISysPermitService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableTransactionManagement
public class SysPermitServiceImpl implements ISysPermitService {
    @Autowired
    private SysPermitMapper sysPermitMapper;

    @Transactional(rollbackFor=Exception.class)
    public int deleteById(SysPermit bean) throws Exception{
        return sysPermitMapper.deleteByPrimaryKey(bean.getId());
    }

    @Transactional(rollbackFor=Exception.class)
    public int save(SysPermit bean) throws Exception{
        return sysPermitMapper.insert(bean);
    }

    @Transactional(rollbackFor=Exception.class)
    public int update(SysPermit bean) throws Exception{
        return sysPermitMapper.updateByPrimaryKeySelective(bean);
    }

    public SysPermit findDataById(SysPermit bean){
        return (SysPermit)sysPermitMapper.selectByPrimaryKey(bean.getId());
    }

    public List<SysPermit> findDataIsPage(SysPermit bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return (List<SysPermit>) sysPermitMapper.selectList(bean);
    }

    public List<SysPermit> findDataIsList(SysPermit bean) {
        return (List<SysPermit>) sysPermitMapper.selectList(bean);
    }


    public int deleteByElementId(String elementId) throws Exception{
        return sysPermitMapper.deletePermitByElementId(elementId);
    }
}
