package cn.com.softvan.api.sys;

import cn.com.softvan.api.sys.utils.RoleIDGenerator;
import cn.com.softvan.utils.*;
import cn.com.softvan.api.BaseController;
import cn.com.softvan.model.LoginUser;
import cn.com.softvan.model.sys.SysRole;
import cn.com.softvan.service.sys.ISysRoleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
@RestController
@RequestMapping(value = "/h/sys/sysRole")
@Slf4j
@ResponseBody
public class SysRoleController extends BaseController {
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private RoleIDGenerator roleIDGenerator;


    /**
     * 新增
     * @param bean
     * @return
     */
    @RequestMapping(value = "/add",method= RequestMethod.POST)
    public Result add(@RequestBody SysRole bean){
        log.info(this.getClass().getName()+"======================add");

        Result result = null;
        if(null != bean){
            if(Validator.isEmpty(bean.getId())){
                bean.setId(IdUtil.createUUID(32));
            }
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setCreateId(user.getId());
                bean.setUpdateId(user.getId());
                bean.setCreateIp(user.getIp());
                bean.setUpdateIp(user.getIp());
                bean.setCompanyId("1001");//该值暂不使用
                sysRoleService.save(bean);
                return Result.success("添加成功！");
            } catch (Exception e) {
                e.printStackTrace();
                return Result.sysError(e);
            }
        }
        else{
            return Result.paramError();
        }
    }

    /**
     * 物理删除
     * @param bean
     * @return
     */
    @RequestMapping(value = "/delete",method=RequestMethod.DELETE)
    public Result delete(SysRole bean){
        log.info(this.getClass().getName()+"======================delete");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setCreateId(user.getId());
                bean.setUpdateId(user.getId());
                bean.setCreateIp(user.getIp());
                bean.setUpdateIp(user.getIp());
                //删除角色和其关联的菜单信息
                sysRoleService.deleteWithMenu(bean);
                result = Result.success("删除成功");
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
     * 逻辑删除
     * @param bean
     * @return
     */
    @RequestMapping(value = "/deleteLogic",method=RequestMethod.DELETE)
    public Result deleteLogic(SysRole bean){
        log.info(this.getClass().getName()+"======================deleteLogic");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setUpdateId(user.getId());
                bean.setUpdateIp(user.getIp());
                bean.setDelFlag("1");
                sysRoleService.update(bean);
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
    public Result update(@RequestBody SysRole bean){
        log.info(this.getClass().getName()+"======================update");
        Result result = null;

        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setCreateId(user.getId());
                bean.setUpdateId(user.getId());
                bean.setCreateIp(user.getIp());
                bean.setUpdateIp(user.getIp());
                sysRoleService.update(bean);
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
    @PreAuthorize("hasAnyRole('admin')")
    public Result selectPage(SysRole bean){
        log.info(this.getClass().getName()+"======================selectPage");
        Result result = null;
        try {
            List<SysRole> list = sysRoleService.findDataIsPage(bean);
            result = Result.success("查询成功！",new PageInfo<>(list));
        } catch (Exception e) {
            e.printStackTrace();
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
    public Result selectList(SysRole bean){
        log.info(this.getClass().getName()+"======================selectList");
        Result result = null;
        try {
            List<SysRole> list = sysRoleService.findDataIsList(bean);
            result = Result.success("查询成功！",list);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.sysError(e);
        }
        return result;
    }

    /**
     * 根据id查询角色
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectById", method = RequestMethod.GET)
    public Result selectById(SysRole bean){
        Result result ;
        try{
            if(Validator.isEmpty(bean.getId())){
                //为空生成id
                bean.setId(roleIDGenerator.getId());
                result = Result.success("新增角色", bean);
                return result;
            }
            SysRole role = sysRoleService.findDataById(bean);
            result = Result.success("查询成功！",role);
        }catch (Exception e){
            e.printStackTrace();
            result = Result.sysError(e);
        }
        return result;
    }


}
