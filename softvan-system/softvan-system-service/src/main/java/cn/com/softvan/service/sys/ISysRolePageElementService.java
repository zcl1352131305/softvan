/*
 * @ClassName ISysRolePageElementService
 * @Description
 * @version 1.0
 * @Date 2017/7/3 4:19:35
 */
package cn.com.softvan.service.sys;


import cn.com.softvan.model.sys.SysRolePageElement;
import cn.com.softvan.service.IBaseService;

public interface ISysRolePageElementService extends IBaseService<SysRolePageElement> {
    int deleteByRoleId(String roleId);

}
