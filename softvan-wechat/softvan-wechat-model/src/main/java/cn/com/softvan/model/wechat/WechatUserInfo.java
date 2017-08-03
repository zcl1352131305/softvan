/*
 * @ClassName WechatUserInfo
 * @Description
 * @version 1.0
 * @Date 2017/7/6 3:37:29
 */
package cn.com.softvan.model.wechat;

import cn.com.softvan.model.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WechatUserInfo extends BaseBean {
	/**
	 * @Fields userName 
	 */
	private String openId;
	/**
	 * @Fields userName 开放平台联合id
	 */
	private String unionId;
	/**
	 * @Fields userName 昵称
	 */
	private String nickName;
	/**
	 * @Fields userName 性别(1:男，2:女，0:未知)
	 */
	private String sex;
	/**
	 * @Fields userName 所在城市
	 */
	private String city;
	/**
	 * @Fields userName 所在国家
	 */
	private String country;
	/**
	 * @Fields userName 所在省份
	 */
	private String province;
	/**
	 * @Fields userName 用户语言
	 */
	private String language;
	/**
	 * @Fields userName 用户头像
	 */
	private String headImgUrl;
	/**
	 * @Fields userName 关注时间
	 */
	private String subscribeTime;
	/**
	 * @Fields userName 是否订阅（0否）
	 */
	private String subscribe;
	/**
	 * @Fields userName 用户备注
	 */
	private String remark;
	/**
	 * @Fields userName 
	 */
	private String groupId;

	public static WechatUserInfo getFromWxMpUser(WxMpUser wxMpUser){
		WechatUserInfo wechatUserInfo = new WechatUserInfo();
		wechatUserInfo.setOpenId(wxMpUser.getOpenId());
		wechatUserInfo.setSubscribe(wxMpUser.getSubscribe()?"1":"0");
		wechatUserInfo.setNickName(wxMpUser.getNickname());
		wechatUserInfo.setSex(wxMpUser.getSex());
		wechatUserInfo.setCity(wxMpUser.getCity());
		wechatUserInfo.setCountry(wxMpUser.getCountry());
		wechatUserInfo.setProvince(wxMpUser.getProvince());
		wechatUserInfo.setLanguage(wxMpUser.getLanguage());
		wechatUserInfo.setHeadImgUrl(wxMpUser.getHeadImgUrl());
		wechatUserInfo.setSubscribeTime(String.valueOf(wxMpUser.getSubscribeTime()));
		wechatUserInfo.setUnionId(wxMpUser.getUnionId());
		wechatUserInfo.setRemark(wxMpUser.getRemark());
		wechatUserInfo.setGroupId(String.valueOf(wxMpUser.getGroupId()));
		return wechatUserInfo;
	}
}
