/*
 * @ClassName IWechatUserInfoService
 * @Description
 * @version 1.0
 * @Date 2017/7/6 3:37:29
 */
package cn.com.softvan.service.wechat;

import cn.com.softvan.model.wechat.WechatUserInfo;
import cn.com.softvan.service.IBaseService;

public interface IWechatUserInfoService extends IBaseService<WechatUserInfo> {
    WechatUserInfo selectByOpenId(String openId);
}
