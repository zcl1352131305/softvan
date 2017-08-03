package cn.com.softvan.utils;

/**
 * Created by vicqiang on 2017/6/1 0001.
 * 返回结果代码类
 */
public enum ResultCode {
    /** 成功 */
    SUCCESS("200", "OK"),

    /** 未授权 */
    AUTH_ERROR("401", "Unauthorized"),

    /***/
    AUTH_FORBIDDEN("403", "FORBIDDEN"),

    /** 系统错误 */
    SYS_ERROR("500", "Internal Server Error"),

    /** 参数错误 */
    PARAMS_ERROR("400", "Bad Request"),

    /**参数无效*/
    PARAMS_INVALID("421", "Invalid params"),

    /** 太频繁的调用 */
    TOO_FREQUENT("445", "太频繁的调用"),

    /**不存在*/
    NOT_FOUND("404", "NOT FOUND");

    ResultCode(String value, String msg){
        this.val = value;
        this.msg = msg;
    }

    public String val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    private String val;
    private String msg;
}
