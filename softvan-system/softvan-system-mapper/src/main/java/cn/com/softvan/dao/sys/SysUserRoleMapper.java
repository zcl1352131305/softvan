/*
 * @ClassName SysUserRoleMapper
 * @Description 
 * @version 1.0
 * @Date 2017-06-20 11:29:27
 */
package cn.com.softvan.dao.sys;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.sys.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserRoleMapper extends IBaseMapper<SysUserRole> {
    int deleteSelective(SysUserRole record);
}