package cn.com.softvan.api.sys;

import cn.com.softvan.utils.*;
import cn.com.softvan.api.BaseController;
import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.model.sys.SysUserRole;
import cn.com.softvan.service.sys.ISysPageElementService;
import cn.com.softvan.service.sys.ISysRolePageElementService;
import cn.com.softvan.service.sys.ISysUserRoleService;
import cn.com.softvan.service.sys.ISysUserService;
import cn.com.softvan.sso.SSOHelper;
import cn.com.softvan.sso.bean.SSOTicket;
import cn.com.softvan.sso.token.ISSOAuthTokenGenerator;
import cn.com.softvan.sso.utils.EncryptUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by lhl on 2017/06/08.
 */
@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class LoginController extends BaseController {
    /**
     * 最大超时时间，超过此时间后当次数据无效 单位：毫秒
     */
    private static final long MAX_TIME_OUT = 1000 * 60;

    private static long TICKET_EXPIRE_TIME = 5 * 60 * 1000;
    /**
     * 登录token加密密钥
     */
    private static final String LOGIN_TOKEN_KEY = "kAtvtRvaUZhsbXRcsrKt9reW5Ywkqk8x";

    /**
     * 用户信息管理
     */
    @Autowired
    private ISysUserService sysUserService;
    /**
     * token生成
     */
    @Autowired
    private ISSOAuthTokenGenerator<SSOTicket> defaultSSOAuthTokenGenerator;
    /**
     * 用户角色管理
     */
    @Autowired
    private ISysUserRoleService sysUserRoleService;


    ///测试
    @Autowired
    private ISysPageElementService sysPageElementService;
    @Autowired
    private ISysRolePageElementService sysRolePageElementService;

    /**
     * 获取随机盐值用于密码加密
     *
     * @return
     */
    @RequestMapping(value = "/salt", method = RequestMethod.GET)
    public Result salt() {
        Result result = null;

        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(String userName, String password, long timestamp, String loginToken) {
        Result result;
        //校验token
        String ip = SSOHelper.getIpAddr(request);
        if (loginToken == null || !loginToken.equals(EncryptUtil.md5(ip + timestamp + LOGIN_TOKEN_KEY))) {
            //token校验失败
            result = new Result(ResultCode.PARAMS_INVALID, "用户名或密码错误！");
            return result;
        }
        //验证时间
        long nowTime = new Date().getTime();
        if (nowTime - timestamp > MAX_TIME_OUT) {
            result = new Result(ResultCode.PARAMS_INVALID, "用户名或密码错误！");
            return result;
        }
        //查询用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser = sysUserService.findDataByUserName(sysUser);
        if (sysUser == null) {
            //用户不存在
            result = new Result(ResultCode.PARAMS_INVALID, "用户名或密码错误！");
            return result;
        }
        //用户密码取hash值BCrypt
        // String hashPassword = BCrypt.hashpw(password, sysUser.getSalt());
        if (BCrypt.checkpw(password, sysUser.getPassword())) {
            //登录成功,生成票据
            if ("1".equals(sysUser.getEnabled())) {
                SSOTicket ticket = new SSOTicket();
                ticket.setUid(sysUser.getId());
                ticket.setIp(request.getRemoteAddr());
                ticket.setExpiration(new Date().getTime());
                ticket.setIsEnabled(sysUser.getEnabled());
                ticket.setOrganization(sysUser.getCompanyId());
                //Role信息
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                List<SysUserRole> userRoles = sysUserRoleService.findDataIsList(sysUserRole);
                if (userRoles != null && !userRoles.isEmpty()) {
                    for (SysUserRole temp : userRoles) {
                        ticket.getRoles().add(temp.getRoleId());
                    }
                }

                String token = defaultSSOAuthTokenGenerator.generateAuthToken(ticket);
                String tokenKey = IdUtil.createUUID(32);
                redisService.set(tokenKey, token, TICKET_EXPIRE_TIME);
                //返回值
                JSONObject returnData = new JSONObject();
                returnData.put("ticket", tokenKey);
                result = Result.success("认证成功", returnData);
            } else {
                //账户已锁定
                result = new Result(ResultCode.PARAMS_INVALID, "账户已被锁定");
            }

        } else {
            result = new Result(ResultCode.PARAMS_INVALID, "用户名或密码错误！");
        }
        return result;
    }

    /**
     * 生成登录token，与时间，ip绑定
     * 防止重放攻击
     *
     * @return
     */
    @RequestMapping(value = "/login/token", method = RequestMethod.GET)
    public Result getLoginToken() {
        Result result = null;
        //获取ip
        String ip = SSOHelper.getIpAddr(request);
        //时间戳
        long timestamp = new Date().getTime();
        String token = EncryptUtil.md5(ip + timestamp + LOGIN_TOKEN_KEY);
        JSONObject data = new JSONObject();
        data.put("timestamp", timestamp);
        data.put("token", token);
        result = new Result(ResultCode.SUCCESS, "获取成功", data);
        return result;
    }

    /**
     * 获取用户授权token
     *
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public Result getAuthToken(String ticket) {
        Result result = null;
        if (Validator.notEmpty(ticket)) {
            String token;
            Object obj = redisService.get(ticket);
            if (obj != null) {
                token = (String) obj;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("token", token);
                result = Result.success("Get Token Success", jsonObject);
            }
        }
        if (result == null) {
            result = new Result();
            result.setCode(ResultCode.PARAMS_INVALID);
            result.setMessage("无效的TICKET");
        }
        return result;
    }

    /**
     * token有效性验证
     * TODO 当前仅验证token是否合法，后续需要根据token失效策略判断，以及进行token重发等操作
     *
     * @param token
     * @param ip
     * @return
     */
    @RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
    public Result checkAuthToken(String token, String ip) {
        Result result = null;
        if (Validator.notEmpty(token)) {
            JSONObject verifyToken = SSOHelper.verifyToken(token, ip);
            if (verifyToken.getBoolean("isOK")) {
                JSONObject data = new JSONObject();
                data.put("isOK", true);
                result = Result.success("校验成功", data);
            }
        }
        if (result == null) {
            result = new Result();
            result.setMessage("校验失败, token已失效");
            result.setCode(ResultCode.AUTH_ERROR);
        }
        return result;
    }


    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public Result refreshToken(String token) {
        Result result = null;


        return result;
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public Result menuTest(String roleId) {
        Result result = null;
       /* List<SysPageElement> all = sysPageElementService.findDataIsList(new SysPageElement());
        //现有
        SysRolePageElement sysRolePageElement = new SysRolePageElement();
        sysRolePageElement.setRoleId(roleId);
        List<SysRolePageElement> now = sysRolePageElementService.findDataIsList(sysRolePageElement);
        //生成Map
        Map<String, SysPageElement> allM = new HashMap<>();
        for (SysPageElement t : all) {
            allM.put(t.getId(), t);
        }

        Set<SysPageElement> rex = new HashSet<>();

        for (SysRolePageElement t : now) {
            SysPageElement n = allM.get(t.getPageElementId());
            if(!"1".equals(n.getParent())){
                SysPageElement parent = allM.get(n.getParent());

                parent.getChild().add(n);
                while (!"1".equals(parent.getParent())) {
                    SysPageElement temp = allM.get(parent.getParent());
                    temp.getChild().add(parent);
                    parent = temp;
                }
                //此時temp為頂級
                boolean isContail = false;
                for (SysPageElement topElement : rex) {
                    if (topElement.getId().equals(parent.getId())) {
                        isContail = true;
                        break;
                    }
                }
                //如果没包含
                if (!isContail) {
                    rex.add(parent);
                }
            }else{
                rex.add(n);
            }
        }*/

           /* SysRolePageElement x = new SysRolePageElement();
            x.setRoleId(roleId);
            result = Result.success("成功", sysPageElementService.findPageElementsByRoleId(x));*/
            return result;
        }
    }
