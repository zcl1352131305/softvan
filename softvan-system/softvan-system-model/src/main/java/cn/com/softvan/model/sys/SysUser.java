/*
 * @ClassName SysUser
 * @Description 
 * @version 1.0
 * @Date 2017-06-20 11:25:01
 */
package cn.com.softvan.model.sys;

import cn.com.softvan.model.BaseBean;
import cn.com.softvan.model.file.CommonFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseBean {
    /**
     * @Fields userName 用户名
     */
    private String userName;
    /**
     * @Fields password 密码
     */
    private String password;

    /**
     * @Fields salt 盐
     */
    private String salt;
    /**
     * @Fields realName 真实姓名
     */
    private String realName;
    /**
     * @Fields gender 1:男2女3保密
     */
    private String gender;
    /**
     * @Fields birthday 生日
     */
    private Date birthday;
    /**
     * @Fields age 年龄
     */
    private String age;
    /**
     * @Fields phone 电话
     */
    private String phone;
    /**
     * @Fields workPhone 工作电话
     */
    private String workPhone;
    /**
     * @Fields email 电子邮件
     */
    private String email;
    /**
     * @Fields headImg 头像
     */
    private String headImg;
    /**
     * @Fields address 住址
     */
    private String address;
    /**
     * @Fields regDate 注册日期
     */
    private Date regDate;
    /**
     * @Fields enabled 1可用的 0不可用
     */
    private String enabled;
    /**
     * @Fields lastLogin 最后登入时间
     */
    private Date lastLogin;
    /**
     * @Fields postCode 邮编
     */
    private String postCode;
    /**
     * @Fields credentialCode 证件号码
     */
    private String credentialCode;
    /**
     * @Fields credential 证件类型
     */
    private String credential;
    /**
     * @Fields position 职位
     */
    private String position;
    /**
     * @Fields companyId 公司ID
     */
    private String companyId;

    /**
     * @Fields 角色
     */
    private List<SysRole> roles;
    /**
     * @Fields 头像
     */
    private List<CommonFile> headImgFile;
}