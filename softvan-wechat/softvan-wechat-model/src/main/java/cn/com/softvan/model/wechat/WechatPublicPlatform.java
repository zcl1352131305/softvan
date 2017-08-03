/*
 * @ClassName WechatPublicPlatform
 * @Description
 * @version 1.0
 * @Date 2017/7/6 10:21:15
 */
package cn.com.softvan.model.wechat;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WechatPublicPlatform extends BaseBean {
	/**
	 * @Fields userName 公众号token
	 */
	private String token;
	/**
	 * @Fields userName 公众号账户
	 */
	private String userId;
	/**
	 * @Fields userName 公众号密码
	 */
	private String password;
	/**
	 * @Fields userName appId
	 */
	private String appId;
	/**
	 * @Fields userName appSecret
	 */
	private String appSecret;

	/**
	 * @Fields aes key
	 */
	private String aesKey;

}
