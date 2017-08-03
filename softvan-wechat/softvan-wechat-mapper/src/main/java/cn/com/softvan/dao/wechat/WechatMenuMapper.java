/*
 * @ClassName WechatMenu
 * @Description
 * @version 1.0
 * @Date 2017/7/6 4:57:4
 */
package cn.com.softvan.dao.wechat;

import cn.com.softvan.dao.IBaseMapper;
import cn.com.softvan.model.wechat.WechatMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WechatMenuMapper extends IBaseMapper<WechatMenu> {

}
