package cn.com.softvan.security;

import cn.com.softvan.utils.PrivilegeConstant;
import cn.com.softvan.utils.Result;
import cn.com.softvan.utils.ResultCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/21
 * Time: 18:10
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Object errObj = request.getAttribute(PrivilegeConstant.ERR_TYPE);
        Result result = new Result();
        if(PrivilegeConstant.NOT_LOGIN.equals(errObj)){
            result.setCode(ResultCode.AUTH_ERROR);
            result.setMessage("用户未登录!");
            response.setStatus(401);
        }else {
            result.setCode(ResultCode.AUTH_FORBIDDEN);
            result.setMessage("用户权限不足!");
            response.setStatus(403);
        }
        JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(result));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(obj.toJSONString());
    }
}
