/*
 * @ClassName WechatUserInfo
 * @Description
 * @version 1.0
 * @Date 2017/7/6 3:37:29
 */
package cn.com.softvan.dao.wechat;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.wechat.WechatUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WechatUserInfoMapper extends IBaseMapper<WechatUserInfo> {
    WechatUserInfo selectByOpenId(String openId);
}
