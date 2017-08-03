package cn.com.softvan.api.wechat.config;

import cn.com.softvan.api.wechat.handler.*;
import cn.com.softvan.model.wechat.WechatPublicPlatform;
import cn.com.softvan.service.wechat.IWechatPublicPlatformService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/06
 * Time: 14:11
 * 微信公众号配置信息
 */
@Configuration
public class WechatConfiguration {
    /**关注事件处理*/
    @Autowired
    private SubscribeEventHandler subscribeEventHandler;
    /***取消关注事件处理*/
    @Autowired
    private UnSubscribeEventHandler unSubscribeEventHandler;
    /**地理位置上报事件*/
    @Autowired
    private LocationEventHandler locationEventHandler;
    /**扫码事件*/
    @Autowired
    private ScanEventHandler scanEventHandler;
    /***默认事件处理*/
    @Autowired
    private DefaultMsgEventHandler defaultMsgEventHandler;
    /**空消息处理（不做处理）*/
    @Autowired
    private NullHandler nullHandler;


    /**公众号信息*/
    @Autowired
    private IWechatPublicPlatformService wechatPublicPlatformService;


    /**创建公众号配置对象**/
    @Bean
    @ConditionalOnMissingBean
    public WxMpConfigStorage configStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        //查询公众号信息
        WechatPublicPlatform wechatPublicPlatform = new WechatPublicPlatform();
        List<WechatPublicPlatform> wechatPublicPlatforms = wechatPublicPlatformService
                .findDataIsList(wechatPublicPlatform);
        if(wechatPublicPlatforms != null && !wechatPublicPlatforms.isEmpty()){
            wechatPublicPlatform = wechatPublicPlatforms.get(0);
            configStorage.setAppId(wechatPublicPlatform.getAppId()); // 设置微信公众号的appid
            configStorage.setSecret(wechatPublicPlatform.getAppSecret()); // 设置微信公众号的app corpSecret
            configStorage.setToken(wechatPublicPlatform.getToken()); // 设置微信公众号的token
            configStorage.setAesKey(wechatPublicPlatform.getAesKey()); // 设置微信公众号的EncodingAESKey
        }
        return configStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService(WxMpConfigStorage configStorage) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }

    @Bean
    public WxMpMessageRouter router(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);
        // 记录所有事件的日志 （异步执行）
        //newRouter.rule().handler(this.logHandler).next();
        // 接收客服会话管理事件
       /* newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
                .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
                .handler(this.kfSessionHandler)
                .end();
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
                .handler(this.kfSessionHandler).end();*/

        // 门店审核事件
       /* newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxMpEventConstants.POI_CHECK_NOTIFY)
                .handler(this.storeCheckNotifyHandler).end();*/

        // 自定义菜单事件
       /* newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.BUTTON_CLICK).handler(this.getMenuHandler()).end();*/

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.BUTTON_VIEW).handler(nullHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_SUBSCRIBE).handler(subscribeEventHandler)
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_UNSUBSCRIBE)
                .handler(unSubscribeEventHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_LOCATION).handler(locationEventHandler)
                .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_LOCATION)
                .handler(locationEventHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_SCAN).handler(scanEventHandler).end();
        // 默认
        newRouter.rule().async(false).handler(defaultMsgEventHandler).end();
        return newRouter;
    }



}
