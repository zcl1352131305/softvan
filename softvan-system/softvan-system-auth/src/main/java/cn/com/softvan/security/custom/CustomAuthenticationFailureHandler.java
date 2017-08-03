package cn.com.softvan.security.custom;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/22
 * Time: 11:40
 * 用户信息认证失败拦截器
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       /* Result result = new Result(ResultCode.AUTH_ERROR);
        JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(result));

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(403);
        response.getWriter().write(obj.toJSONString());*/
        response.sendError(403, "user disabled");
    }
}
