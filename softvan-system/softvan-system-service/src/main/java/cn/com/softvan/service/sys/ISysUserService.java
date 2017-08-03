package cn.com.softvan.service.sys;

import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.service.IBaseService;

/**
 * Created by vicqiang on 2017/6/1 0001.
 */
public interface ISysUserService extends IBaseService<SysUser> {
    SysUser findDataByUserName(SysUser bean);
}
