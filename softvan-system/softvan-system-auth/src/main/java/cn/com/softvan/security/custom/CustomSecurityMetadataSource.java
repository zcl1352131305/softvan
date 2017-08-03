package cn.com.softvan.security.custom;

import cn.com.softvan.config.AppConfig;
import cn.com.softvan.security.SecurityUser;
import cn.com.softvan.security.privilege.PrivilegeDataSource;
import cn.com.softvan.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/09
 * Time: 10:38
 * 自定义权限加载，实现权限动态控制
 * 从数据库加载一个url所需要的权限
 */

@AutoConfigureBefore(AppConfig.class)
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private PrivilegeDataSource privilegeDataSource;

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取访问对象
        FilterInvocation fi = (FilterInvocation) object;
        //获取用户信息
        SecurityUser securityUser = (SecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String permitUrlPattern = AppConfig.getProperty("permitUrlPattern");
        String url = fi.getRequestUrl();
        boolean needAuth = true;
        if(Validator.notEmpty(permitUrlPattern)){
            String []patterns = permitUrlPattern.split(",");
            for (int i=0 ;i <patterns.length;i++){
                if(url.indexOf(patterns[i])!=-1){
                    needAuth = false;
                    break;
                }
            }
        }
        Collection<ConfigAttribute> metadataSource = new ArrayList<>();
        if(needAuth){
            metadataSource = privilegeDataSource.privilegeCollection(fi, securityUser);
        }
        else{
            metadataSource.add(new SecurityConfig("1000"));
        }
        //获取所需权限


        return metadataSource;
    }
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
