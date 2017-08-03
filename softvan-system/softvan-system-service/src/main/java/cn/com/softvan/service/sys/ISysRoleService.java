/*
 * @ClassName ISysRoleService
 * @Description
 * @version 1.0
 * @Date 2017/6/30 11:58:51
 */
package cn.com.softvan.service.sys;

import cn.com.softvan.model.sys.SysRole;
import cn.com.softvan.model.sys.SysUserRole;
import cn.com.softvan.service.IBaseService;

import java.util.List;
import java.util.Map;

public interface ISysRoleService extends IBaseService<SysRole> {
    List<Map<String, String>>  selectRolePermit(SysRole sysRole);
    List<SysRole> selectUserRole(SysUserRole sysUserRole);

    /**
     * 删除，同时删除关联菜单
     * @param bean
     * @return
     */
    int deleteWithMenu(SysRole bean) throws Exception;
}
