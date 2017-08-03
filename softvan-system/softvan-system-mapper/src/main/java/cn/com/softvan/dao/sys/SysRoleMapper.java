/*
 * @ClassName SysRole
 * @Description
 * @version 1.0
 * @Date 2017/6/30 11:58:51
 */
package cn.com.softvan.dao.sys;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.sys.SysRole;
import cn.com.softvan.model.sys.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SysRoleMapper extends IBaseMapper<SysRole> {
    List<Map<String, String>> selectRolePermit(SysRole sysRole);
    List<SysRole> selectUserRole(SysUserRole sysUserRole);
}
