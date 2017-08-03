/*
 * @ClassName SysPageElement
 * @Description
 * @version 1.0
 * @Date 2017/6/30 4:59:11
 */
package cn.com.softvan.model.sys;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPageElement extends BaseBean {
	/**
	 * @Fields userName 显示名称
	 */
	private String title;
	/**
	 * @Fields icon 图标
	 */
	private String icon;
	/**
	 * @Fields userName 显示访问地址
	 */
	private String url;
	/**
	 * @Fields userName 拦截标识
	 */
	private String interception;
	/**
	 * @Fields userName 类型(MENU菜单, BUTTON按钮)
	 */
	private String type;
	/**
	 * @Fields userName 父级id
	 */
	private String parent;
	/**
	 * @Fields userName 排序
	 */
	private String sort;
	/**
	 * @Fields grade 菜单等级
	 */
	private String grade;
	/**
	 * @Fields userName 应用id
	 */
	private String appId;
	/**
	 * @Fields 权限
	 */
	private List<SysPermit> permits;

	private List<SysPageElement> child = new ArrayList<>();

}
