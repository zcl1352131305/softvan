package cn.com.softvan.service.sys.impl;

import cn.com.softvan.dao.sys.SysRolePermitMapper;
import cn.com.softvan.model.sys.SysRolePermit;
import cn.com.softvan.service.sys.ISysRolePermitService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
@Service
public class SysRolePermitServiceImpl implements ISysRolePermitService {
    @Autowired
    private SysRolePermitMapper sysRolePermitMapper;

    public SysRolePermit findDataById(SysRolePermit bean){
        return (SysRolePermit)sysRolePermitMapper.selectByPrimaryKey(bean.getId());
    }

    public int deleteById(SysRolePermit bean) throws Exception{
        return sysRolePermitMapper.deleteByPrimaryKey(bean.getId());
    }

    public int save(SysRolePermit bean) throws Exception{
        return sysRolePermitMapper.insert(bean);
    }

    public int update(SysRolePermit bean) throws Exception{
        return sysRolePermitMapper.updateByPrimaryKeySelective(bean);
    }

    public List<SysRolePermit> findDataIsPage(SysRolePermit bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return (List<SysRolePermit>) sysRolePermitMapper.selectList(bean);
    }

    public List<SysRolePermit> findDataIsList(SysRolePermit bean) {
        return (List<SysRolePermit>) sysRolePermitMapper.selectList(bean);
    }

    public  List<SysRolePermit> selectAllPermit(SysRolePermit bean){
        return sysRolePermitMapper.selectAllPermit(bean);
    }
}
