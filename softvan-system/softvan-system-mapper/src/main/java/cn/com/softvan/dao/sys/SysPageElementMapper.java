/*
 * @ClassName SysPageElement
 * @Description
 * @version 1.0
 * @Date 2017/6/30 4:59:11
 */
package cn.com.softvan.dao.sys;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.sys.SysPageElement;
import cn.com.softvan.model.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysPageElementMapper extends IBaseMapper<SysPageElement> {
    List<SysPageElement> selectUserMenu(SysUser record);
}
