/*
 * @ClassName SysRolePageElement
 * @Description
 * @version 1.0
 * @Date 2017/7/3 4:19:35
 */
package cn.com.softvan.model.sys;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRolePageElement extends BaseBean {
	/**
	 * @Fields userName 角色ID
	 */
	private String roleId;
	/**
	 * @Fields userName 页面元素ID
	 */
	private String pageElementId;
	/**
	 * @Fields userName 拥有权限
	 */
	private String hasPermit;

}
