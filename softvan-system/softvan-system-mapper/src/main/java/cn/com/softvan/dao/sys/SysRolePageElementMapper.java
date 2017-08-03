/*
 * @ClassName SysRolePageElement
 * @Description
 * @version 1.0
 * @Date 2017/7/3 4:19:35
 */
package cn.com.softvan.dao.sys;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.sys.SysRolePageElement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysRolePageElementMapper extends IBaseMapper<SysRolePageElement> {
    int deleteByRoleId(String roleId);
}
