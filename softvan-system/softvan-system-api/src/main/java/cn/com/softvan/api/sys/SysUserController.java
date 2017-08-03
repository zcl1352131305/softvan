package cn.com.softvan.api.sys;

import cn.com.softvan.service.file.ICommonFileService;
import cn.com.softvan.sso.utils.EncryptUtil;
import cn.com.softvan.utils.*;
import cn.com.softvan.api.BaseController;
import cn.com.softvan.model.LoginUser;
import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.service.sys.ISysUserRoleService;
import cn.com.softvan.service.sys.ISysUserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zcl on 2017/6/1 0001.
 */
@RestController
@RequestMapping(value = "/h/sys/sysUser")
@Slf4j
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ICommonFileService fileService;

    /**
     * 新增
     * @param bean
     * @return
     */
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    public Result add(@RequestBody SysUser bean){
        log.info(this.getClass().getName()+"======================add");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getUserName()) && Validator.notEmpty(bean.getPassword())){
            if(Validator.isEmpty(bean.getId())){
                bean.setId(IdUtil.createUUID(32));
            }
            try {

                SysUser user1 = sysUserService.findDataByUserName(bean);
                if(user1 == null){
                    LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                    bean.setCreateId(user.getId());
                    bean.setUpdateId(user.getId());
                    bean.setCreateIp(user.getIp());
                    bean.setUpdateIp(user.getIp());
                    //用户密码加密规则：（MD5(用户名+用户密码）+ salt）
                    //密码加盐hash
                    bean.setSalt(BCrypt.gensalt(10));
                    String tempPassword = EncryptUtil.md5(bean.getUserName()+bean.getPassword());
                    bean.setPassword(BCrypt.hashpw(tempPassword, bean.getSalt()));
                    sysUserService.save(bean);
                    result = Result.success("添加成功！");
                }
                else{
                    result = Result.paramError("用户已存在！");
                }
            } catch (Exception e) {
                result = Result.sysError(e);
            }
        }
        else{
            result = Result.paramError();
        }
        return result;
    }

    /**
     * 物理删除
     * @param bean
     * @return
     */
    @RequestMapping(value = "/delete",method=RequestMethod.DELETE)
    public Result delete(SysUser bean){
        log.info(this.getClass().getName()+"======================delete");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                sysUserService.deleteById(bean);
                result = Result.success("删除成功");
            } catch (Exception e) {
                result = Result.sysError(e);
            }
        }
        else{
            result = Result.paramError();
        }
        return result;
    }

    /**
     * 逻辑删除
     * @param bean
     * @return
     */
    @RequestMapping(value = "/deleteLogic",method=RequestMethod.DELETE)
    public Result deleteLogic(SysUser bean){
        log.info(this.getClass().getName()+"======================deleteLogic");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setUpdateId(user.getId());
                bean.setUpdateIp(user.getIp());
                bean.setDelFlag("1");
                sysUserService.update(bean);
                result = Result.success("删除成功");
            } catch (Exception e) {
                result = Result.sysError(e);
            }
        }
        else{
            result = Result.paramError();
        }
        return result;
    }

    /**
     * 修改
     * @param bean
     * @return
     */
    @RequestMapping(value = "/update",method=RequestMethod.PUT)
    @ResponseBody
    public Result update(@RequestBody SysUser bean){
        log.info(this.getClass().getName()+"======================update");
        Result result = null;

        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setUpdateId(user.getId());
                bean.setUpdateIp(user.getIp());

                if(Validator.notEmpty(bean.getPassword())){
                    bean.setSalt(BCrypt.gensalt(10));
                    String tempPassword = EncryptUtil.md5(bean.getUserName()+bean.getPassword());
                    bean.setPassword(BCrypt.hashpw(tempPassword, bean.getSalt()));
                }

                sysUserService.update(bean);

                //保存用户头像
                /*if(null !=  bean.getHeadImgFile()){
                    fileService.saveFile(bean.getId(),"headImg",bean.getHeadImgFile());
                }*/
                redisService.clear();

                result = Result.success("更新成功！");
            } catch (Exception e) {
                e.printStackTrace();
                result = Result.sysError(e);
            }
        }
        else{
            result = Result.paramError();
        }
        return result;
    }

    /**
     * 分页查询
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectPage",method=RequestMethod.GET)
    public Result selectPage(SysUser bean){
        log.info(this.getClass().getName()+"======================selectPage");
        Result result = null;
        try {
            List<SysUser> list = sysUserService.findDataIsPage(bean);
            result = Result.success("查询成功！",new PageInfo<>(list));
        } catch (Exception e) {
            result = Result.sysError(e);
        }
        return result;
    }

    /**
     * 列表查询
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectList",method=RequestMethod.GET)
    public Result selectList(SysUser bean){
        log.info(this.getClass().getName()+"======================selectList");
        Result result = null;
        try {
            List<SysUser> list = sysUserService.findDataIsList(bean);
            result = Result.success("查询成功！",list);
        } catch (Exception e) {
            result = Result.sysError(e);
        }
        return result;
    }

    /**
     * ID查询
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectById",method=RequestMethod.GET)
    public Result selectById(SysUser bean){
        log.info(this.getClass().getName()+"======================selectById");
        Result result = null;
        try {
            SysUser rsBean = sysUserService.findDataById(bean);
            result = Result.success("查询成功！",rsBean);
        } catch (Exception e) {
            result = Result.sysError(e);
        }
        return result;
    }



}
