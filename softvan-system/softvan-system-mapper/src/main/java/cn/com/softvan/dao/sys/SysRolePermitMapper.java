/*
 * @ClassName SysRolePermitMapper
 * @Description 
 * @version 1.0
 * @Date 2017-06-20 11:30:12
 */
package cn.com.softvan.dao.sys;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.BaseBean;
import cn.com.softvan.model.sys.SysRolePermit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRolePermitMapper extends IBaseMapper<SysRolePermit> {
    List<SysRolePermit> selectAllPermit(BaseBean bean);
}