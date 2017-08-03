package cn.com.softvan.security;

import cn.com.softvan.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vicqiang on 2017/6/7 0007.
 */
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {
    public void onLogoutSuccess(HttpServletRequest var1, HttpServletResponse var2, Authentication var3) throws IOException, ServletException{
        Result result = Result.success("登出成功！");
        JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(result));
        var2.getWriter().write(obj.toJSONString());
    }
}
