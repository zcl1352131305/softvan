/*
 * @ClassName SysRole
 * @Description
 * @version 1.0
 * @Date 2017/6/29 11:25:15
 */
package cn.com.softvan.model.sys;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole extends BaseBean {
    /**
     * @Fields userName 公司id
     */
    private String companyId;
    /**
     * @Fields userName 用户名
     */
    private String roleName;
    /**
     * @Fields userName 优先级
     */
    private String priority;

    private List<SysRolePageElement> sysRolePageElements;
}
