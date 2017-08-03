/*
 * @ClassName SysUserMapper
 * @Description 
 * @version 1.0
 * @Date 2017-06-20 11:25:01
 */
package cn.com.softvan.dao.sys;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.BaseBean;
import cn.com.softvan.model.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserMapper extends IBaseMapper<SysUser> {
    SysUser selectByUserName(BaseBean bean);

}