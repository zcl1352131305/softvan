package cn.com.softvan.security;

import cn.com.softvan.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest var1, HttpServletResponse var2, Authentication var3) throws IOException, ServletException{
        Result result = Result.success("验证成功！");
        JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(result));
        var2.getWriter().write(obj.toJSONString());
    }
}
