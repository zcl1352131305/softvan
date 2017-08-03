package cn.com.softvan.service.sys;

import cn.com.softvan.model.sys.SysRolePermit;
import cn.com.softvan.service.IBaseService;

import java.util.List;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
public interface ISysRolePermitService extends IBaseService<SysRolePermit> {
    List<SysRolePermit> selectAllPermit(SysRolePermit bean);
}
