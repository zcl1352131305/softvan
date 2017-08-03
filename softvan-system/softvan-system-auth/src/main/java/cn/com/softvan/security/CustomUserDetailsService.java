package cn.com.softvan.security;

import cn.com.softvan.model.LoginUser;
import cn.com.softvan.model.sys.SysRole;
import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.service.sys.ISysRoleService;
import cn.com.softvan.service.sys.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        SysUser userVo = null;
        try {
            userVo = sysUserService.findDataByUserName(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(null == userVo){
            throw new UsernameNotFoundException("UserName"+userName+"not found");
        }
        sysUser.setId(userVo.getId());
        List<SysRole> roles = new ArrayList<SysRole>();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        try {
            SysRole role = new SysRole();
            role.setId(userVo.getId());
            roles = sysRoleService.findDataIsList(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(SysRole role :roles){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        LoginUser loginUser = new LoginUser();
        loginUser.setId(userVo.getId());
        loginUser.setUserName(userName);
        loginUser.setUserName(userVo.getUserName());
        loginUser.setPassword(userVo.getPassword());
        loginUser.setEnabled("1");

        SecurityUser user = new SecurityUser(loginUser,grantedAuthorities);
        return user;

    }
}
