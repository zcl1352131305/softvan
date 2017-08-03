/*
 * @ClassName SysPermit
 * @Description
 * @version 1.0
 * @Date 2017/6/30 5:54:20
 */
package cn.com.softvan.dao.sys;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.sys.SysPermit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysPermitMapper extends IBaseMapper<SysPermit> {
    int deletePermitByElementId(String pageElementId) throws Exception;
}
