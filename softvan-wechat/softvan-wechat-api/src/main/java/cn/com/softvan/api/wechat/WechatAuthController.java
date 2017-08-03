package cn.com.softvan.api.wechat;

import cn.com.softvan.api.BaseController;
import cn.com.softvan.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/10
 * Time: 16:07
 * 微信用户登录认证控制器
 */
@RestController
@RequestMapping(value = "/m/wechat/auth")
@Slf4j
public class WechatAuthController extends BaseController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test(){
        return Result.success("访问成功",request.getAttribute("u"));
    }
}
