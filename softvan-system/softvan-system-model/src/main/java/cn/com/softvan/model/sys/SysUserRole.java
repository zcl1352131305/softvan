/*
 * @ClassName SysUserRole
 * @Description 
 * @version 1.0
 * @Date 2017-06-20 11:29:27
 */
package cn.com.softvan.model.sys;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends BaseBean {
    /**
     * @Fields userId 用户ID
     */
    private String userId;
    /**
     * @Fields roleId 角色ID
     */
    private String roleId;
    /**
     * @Fields companyId 公司id
     */
    private Integer companyId;
}