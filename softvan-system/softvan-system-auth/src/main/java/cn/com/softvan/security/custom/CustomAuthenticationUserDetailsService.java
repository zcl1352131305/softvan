package cn.com.softvan.security.custom;

import cn.com.softvan.model.LoginUser;
import cn.com.softvan.security.SecurityUser;
import cn.com.softvan.sso.SSOHelper;
import cn.com.softvan.utils.PrivilegeConstant;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/09
 * Time: 11:24
 * 自定义token认证，判定用户token是否有效，若有效则加载并返回用户信息
 */
public class CustomAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        JSONObject authData = (JSONObject) token.getPrincipal();
        String principal = authData.getString("token");
        LoginUser sysUser = new LoginUser();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (!StringUtils.isEmpty(principal)) {
            JSONObject verifyData = SSOHelper.verifyToken(principal, authData.getString("ip"));
            if (verifyData.getBoolean("isOK")) {
                JSONObject payload = verifyData.getJSONObject("payload");
                //获取用户角色信息
                JSONArray roles = payload.getJSONArray("r");
                if (roles != null) {
                    for (Object obj : roles) {
                        grantedAuthorities.add(new SimpleGrantedAuthority((String) obj));
                    }
                }
                //登录用户默认拥有匿名用户权限
                grantedAuthorities.add(new SimpleGrantedAuthority(PrivilegeConstant.ANONYMOUS_ACCESS));
                //添加已登录用户权限
                grantedAuthorities.add(new SimpleGrantedAuthority(PrivilegeConstant.VERIFIED_ACCESS));
                sysUser.setId(payload.getString("uid"));
                sysUser.setEnabled(payload.getString("e"));
                sysUser.setCompanyId(payload.getString("org"));
                sysUser.setIp(payload.getString("ip"));
                return new SecurityUser(sysUser, grantedAuthorities, false);
            } else {
                //token验证失败同样视为未登录用户
                sysUser.setEnabled("1");
                grantedAuthorities.add(new SimpleGrantedAuthority(PrivilegeConstant.ANONYMOUS_ACCESS));
                return new SecurityUser(sysUser, grantedAuthorities);
            }
        }
        //throw new UsernameNotFoundException("授权错误！");
        //不存在返回一个匿名用户
        sysUser.setEnabled("1");
        grantedAuthorities.add(new SimpleGrantedAuthority(PrivilegeConstant.ANONYMOUS_ACCESS));
        return new SecurityUser(sysUser, grantedAuthorities);
    }
}