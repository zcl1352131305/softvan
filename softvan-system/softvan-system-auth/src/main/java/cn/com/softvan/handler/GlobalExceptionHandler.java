package cn.com.softvan.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/23
 * Time: 9:58
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = DisabledException.class)
    @ResponseBody
    public JSONObject defaultExceptionHandle(HttpServletRequest req, Exception e){
        JSONObject obj = new JSONObject();
        obj.put("code", "402");
        obj.put("msg", "用户已锁定1");

        return obj;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject gloableServerException(HttpServletRequest req, Exception e){
        JSONObject obj = new JSONObject();
        obj.put("code", "500");
        obj.put("msg", "Internal Server Exception");
        return  obj;
    }
}
