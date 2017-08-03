package cn.com.softvan.sso.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/12
 * Time: 14:50
 * SSO登录票据
 */
@Data
public class SSOTicket {
    /**用户id*/
    private String uid;
    /**所属机构*/
    private String organization;
    /**过期时间*/
    private long expiration;
    /**ip地址**/
    private String ip;
    /**数据*/
    private String data;
    /**是否有效*/
    private String isEnabled;
    /**角色*/
    private List<String> roles = new ArrayList<>();
    /**已登录系统集合*/
    private List<String> appIds = new ArrayList<>();

}
