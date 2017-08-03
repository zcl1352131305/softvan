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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/06
 * Time: 14:39
 * 用户取消关注事件处理
 */
@Component
@Slf4j
public class UnSubscribeEventHandler implements WxMpMessageHandler {

    @Autowired
    private IWechatUserInfoService wechatUserInfoService;
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        log.debug("收到取消订阅事件推送");
        WechatUserInfo wechatUserInfo = wechatUserInfoService.selectByOpenId(wxMessage.getFromUser());
        if(wechatUserInfo != null){
            //更新用户订阅状态
            WechatUserInfo updateWechatUserInfo = new WechatUserInfo();
            updateWechatUserInfo.setSubscribe("0");
            updateWechatUserInfo.setId(wechatUserInfo.getId());
            try {
                wechatUserInfoService.update(updateWechatUserInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
