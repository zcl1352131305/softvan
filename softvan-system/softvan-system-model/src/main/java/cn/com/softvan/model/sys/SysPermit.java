/*
 * @ClassName SysPermit
 * @Description
 * @version 1.0
 * @Date 2017/6/30 5:54:20
 */
package cn.com.softvan.model.sys;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermit extends BaseBean {
	/**
	 * @Fields userName 页面元素ID
	 */
	private String pageElementId;
	/**
	 * @Fields userName 应用id
	 */
	private String appId;
	/**
	 * @Fields userName 访问链接
	 */
	private String url;
	/**
	 * @Fields userName 父级权限
	 */
	private String pPermit;
	/**
	 * @Fields type 权限类型
	 */
	private String type;

}
