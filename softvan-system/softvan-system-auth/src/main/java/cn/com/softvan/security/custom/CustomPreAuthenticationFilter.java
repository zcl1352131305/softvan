package cn.com.softvan.security.custom;

import cn.com.softvan.utils.Validator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/09
 * Time: 11:10
 * 自定义token拦截
 * 用户权鉴采用token机制
 */
public class CustomPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    public static final String SSO_TOKEN = "X-Auth-Token";
    public static final String SSO_CREDENTIALS = "N/A";

    public CustomPreAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationFailureHandler authenticationFailureHandler) {
        setAuthenticationManager(authenticationManager);
        //失败处理
         //setAuthenticationFailureHandler(authenticationFailureHandler);
        //该项设置为false则token验证失败结束，否则会以匿名身份继续验证权限

        setContinueFilterChainOnUnsuccessfulAuthentication(false);
    }


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {

        //获取token信息，若头部不存在尝试从cookie中获取
        JSONObject result = new JSONObject();
        String token = request.getHeader(SSO_TOKEN);
        if(Validator.isEmpty(token)){
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (Cookie cookie : cookies){
                    if(SSO_TOKEN.equals(cookie.getName())){
                        token = cookie.getValue();
                        break;
                    }
                }
            }

        }
        result.put("ip", request.getRemoteAddr());
        result.put("token", token);
        return result;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return SSO_CREDENTIALS;
    }
}