package cn.com.softvan.security;

import cn.com.softvan.utils.Result;
import cn.com.softvan.utils.ResultCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vicqiang on 2017/6/7 0007.
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException var3) throws IOException, ServletException{
        Result result = new Result(ResultCode.AUTH_ERROR);
        JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(result));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(obj.toJSONString());
    }

}
