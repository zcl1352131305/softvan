package cn.com.softvan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser extends BaseBean{
    /**
     * @Fields userName 用户名
     */
    private String userName;
    /**
     * @Fields passWord 密码
     */
    private String password;
    /**w
     * @Fields ip
     */
    private String ip;
    /**
     * @Fields companyId 公司ID
     */
    private String companyId;
    /**
     * @Fields enabled 1可用的 0不可用
     */
    private String enabled;
}
