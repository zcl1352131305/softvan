package cn.com.softvan.security.privilege;

import cn.com.softvan.model.sys.SysRolePermit;
import cn.com.softvan.security.SecurityUser;
import cn.com.softvan.service.IRedisService;
import cn.com.softvan.service.sys.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/06/09
 * Time: 11:50
 */
@Component
public class PrivilegeDataSource {
    /**缓存管理*/
    @Autowired
    protected IRedisService redisService;
    @Autowired
    private ISysRoleService sysRoleService;


    public Collection<ConfigAttribute> privilegeCollection(FilterInvocation fi, SecurityUser securityUser){
        Collection<ConfigAttribute> needPermit = new ArrayList<>();
        List<Map<String, String>>  sysRolePermits = getAllSysRolePermit(securityUser);
        HttpServletRequest request = fi.getRequest();
        if(sysRolePermits != null && !sysRolePermits.isEmpty()){
            for(Map<String,String> permit: sysRolePermits){
                    RequestMatcher requestMatcher = new AntPathRequestMatcher(permit.get("url"));
                    if (requestMatcher.matches(request)) {
                        //匹配成功加入所需权限当中
                        needPermit.add(new SecurityConfig(permit.get("roleId")));
                    }
            }
        }
        return needPermit;
    }


    /**
     * 查询系统权限角色关系
     * KEY:角色id
     * Value：具体链接
     * @param securityUser
     * @return
     */
    private  List<Map<String, String>>  getAllSysRolePermit(SecurityUser securityUser){
        SysRolePermit sysRolePermit = new SysRolePermit();
        sysRolePermit.setCompanyId(securityUser.getOrganization());
        //TODO 添加从缓存获取机制
        List<Map<String, String>>  sysRolePermits = sysRoleService.selectRolePermit(null);
        return sysRolePermits;
    }


}
