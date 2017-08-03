package cn.com.softvan.api.wechat.handler;

import cn.com.softvan.model.wechat.WechatUserInfo;
import cn.com.softvan.service.wechat.IWechatUserInfoService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/05
 * Time: 18:47
 * 微信用户关注事件处理器
 */
@Component
@Slf4j
public class SubscribeEventHandler implements WxMpMessageHandler {

    /**微信关注用户信息管理*/
    @Autowired
    private IWechatUserInfoService wechatUserInfoService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        log.debug("收到订阅事件推送");
        //获取用户详情
        WxMpUser wxMpUser =  wxMpService.getUserService().userInfo(wxMessage.getFromUser());
        //保存用户信息
        WechatUserInfo wechatUserInfo = WechatUserInfo.getFromWxMpUser(wxMpUser);
        try {
            wechatUserInfoService.save(wechatUserInfo);
        } catch (Exception e) {
            log.error("保存关注微信用户信息异常");
            e.printStackTrace();
        }
        //下发欢迎消息
        return  WxMpXmlOutMessage.TEXT()
                .content("欢迎关注本公众号！！！！！")
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
    }
}
