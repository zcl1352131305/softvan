package cn.com.softvan.utils;

/**
 * Created by lhl on 2017/06/16.
 */
public class PrivilegeConstant {
    /***匿名权限*/
    public static final String ANONYMOUS_ACCESS = "1000";
    /**已登录用户权限**/
    public static final String VERIFIED_ACCESS = "1001";

    /**用户信息变量名*/
    public static final String CONSTANT_USER_INFO = "CONSTANT_USER_INFO";
    /**权限验证失败标识*/
    public static final String ERR_TYPE = "ERR_TYPE";
    /**************失败类型******************/
    /**未登录**/
    public static final String NOT_LOGIN = "NOT_LOGIN";
    /**权限不足**/
    public static final String INSUFFICIENT_PERMISSIONS = "INSUFFICIENT_PERMISSIONS";
}
