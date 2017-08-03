package cn.com.softvan.security;

import cn.com.softvan.model.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
public class SecurityUser implements UserDetails{

    private LoginUser user;
    /**用户权限信息*/
    private List<SimpleGrantedAuthority> grantedAuthorityList;
    /**当前是否为匿名访问（默认true)**/
    private boolean isAnonymous;

    public SecurityUser(LoginUser user, List<SimpleGrantedAuthority> grantedAuthorityList){
        this.grantedAuthorityList = grantedAuthorityList;
        this.user = user;
        this.isAnonymous = true;
    }

    public SecurityUser(LoginUser user, List<SimpleGrantedAuthority> grantedAuthorityList, boolean isAnonymous){
        this.grantedAuthorityList = grantedAuthorityList;
        this.user = user;
        this.isAnonymous = isAnonymous;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return "1".equals(user.getEnabled());
    }


    //////////////////自定义信息//////////////////////

    /**
     * 获取用户所属组织信息
     * @return
     */
    public String getOrganization(){
        return user.getCompanyId();
    }

    /**
     * 获取用户是否属于未登录
     * @return
     */
    public boolean getIsAnonymous(){
        return this.isAnonymous;
    }

    /**
     * 获取用户信息
     * @return
     */
    public LoginUser getUser(){return this.user;}

}
