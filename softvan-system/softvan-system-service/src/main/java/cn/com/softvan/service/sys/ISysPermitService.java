/*
 * @ClassName ISysPermitService
 * @Description
 * @version 1.0
 * @Date 2017/6/30 5:54:20
 */
package cn.com.softvan.service.sys;

import cn.com.softvan.model.sys.SysPermit;
import cn.com.softvan.service.IBaseService;

public interface ISysPermitService extends IBaseService<SysPermit> {
    int deleteByElementId(String elementId) throws Exception;
}
