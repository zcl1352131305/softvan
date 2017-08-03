/*
 * @ClassName WechatMenu
 * @Description
 * @version 1.0
 * @Date 2017/7/6 4:57:4
 */
package cn.com.softvan.model.wechat;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WechatMenu extends BaseBean {
	/**
	 * @Fields userName 类型
	 */
	private String type;
	/**
	 * @Fields userName 名称
	 */
	private String name;
	/**
	 * @Fields userName 菜单key值
	 */
	private String key;
	/**
	 * @Fields userName 菜单地址
	 */
	private String url;
	/**
	 * @Fields userName 
	 */
	private String mediaId;
	/**
	 * @Fields userName 小程序appid
	 */
	private String appid;
	/**
	 * @Fields userName 小程序路径
	 */
	private String pagepath;
	/**
	 * @Fields userName 父级菜单id
	 */
	private String parent;
	/**
	 * @Fields userName 排序
	 */
	private String sort;

}
