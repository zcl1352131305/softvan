/*
 * @ClassName ISysPageElementService
 * @Description
 * @version 1.0
 * @Date 2017/6/30 4:59:11
 */
package cn.com.softvan.service.sys;

import cn.com.softvan.model.sys.SysMenu;
import cn.com.softvan.model.sys.SysPageElement;
import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.service.IBaseService;

import java.util.List;


public interface ISysPageElementService extends IBaseService<SysPageElement> {
    List<SysMenu> findPageElementsByRoleId(SysUser user);
}
