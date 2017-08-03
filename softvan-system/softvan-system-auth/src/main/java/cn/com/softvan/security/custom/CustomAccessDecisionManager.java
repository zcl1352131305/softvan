package cn.com.softvan.security.custom;

import cn.com.softvan.security.SecurityUser;
import cn.com.softvan.utils.PrivilegeConstant;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/09
 * Time: 14:16
 * 自定义决策器，判定权限是否符合要求
 */
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        FilterInvocation fi = (FilterInvocation)object;
        if (authentication == null) {
            //标记为未登录
            fi.getHttpRequest().setAttribute(PrivilegeConstant.ERR_TYPE, PrivilegeConstant.NOT_LOGIN);
            throw new AccessDeniedException("请登录!");
        }
        //获取用户信息
        SecurityUser securityUser = (SecurityUser)authentication.getPrincipal();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            String needCode = configAttribute.getAttribute();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (needCode.equals(authority.getAuthority())) {
                    //成功，在request中存储user信息
                    fi.getHttpRequest().setAttribute(PrivilegeConstant.CONSTANT_USER_INFO, securityUser.getUser());
                    return;
                }
            }
        }
        //若权限不足，判断用户属于登录无权限还是未登录
        if(securityUser.getIsAnonymous()){
            fi.getHttpRequest().setAttribute(PrivilegeConstant.ERR_TYPE, PrivilegeConstant.NOT_LOGIN);
        }else {
            fi.getHttpRequest().setAttribute(PrivilegeConstant.ERR_TYPE, PrivilegeConstant.INSUFFICIENT_PERMISSIONS);
        }
        throw new AccessDeniedException("当前访问没有权限");
    }
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
