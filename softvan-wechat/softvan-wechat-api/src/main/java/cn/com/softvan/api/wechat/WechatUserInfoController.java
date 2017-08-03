/*
 * @ClassName WechatUserInfoController
 * @Description
 * @version 1.0
 * @Date 2017/7/6 3:37:29
 */
package cn.com.softvan.api.wechat;

import cn.com.softvan.utils.*;
import cn.com.softvan.api.BaseController;
import cn.com.softvan.model.LoginUser;
import cn.com.softvan.model.wechat.WechatUserInfo;
import cn.com.softvan.service.wechat.IWechatUserInfoService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/h/wechat/wechatUserInfo")
@Slf4j
public class WechatUserInfoController extends BaseController {
    @Autowired
    private IWechatUserInfoService wechatUserInfoService;

    /**
     * 新增
     * @param bean
     * @return
     */
    @RequestMapping(value = "/add",method= RequestMethod.POST)
    public Result add(@RequestBody WechatUserInfo bean){
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
                wechatUserInfoService.save(bean);
                result = Result.success("添加成功！");
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
    public Result delete(WechatUserInfo bean){
        log.info(this.getClass().getName()+"======================delete");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                wechatUserInfoService.deleteById(bean);
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
    public Result deleteLogic(WechatUserInfo bean){
        log.info(this.getClass().getName()+"======================deleteLogic");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setUpdateId(user.getId());
                bean.setUpdateIp(user.getIp());
                wechatUserInfoService.update(bean);
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
    public Result update(@RequestBody WechatUserInfo bean){
        log.info(this.getClass().getName()+"======================update");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setUpdateId(user.getId());
                bean.setUpdateIp(user.getIp());
                wechatUserInfoService.update(bean);
                result = Result.success("更新成功！");
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
     * 分页查询
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectPage",method=RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public Result selectPage(WechatUserInfo bean){
        log.info(this.getClass().getName()+"======================selectPage");
        Result result = null;
        try {
            List<WechatUserInfo> list = wechatUserInfoService.findDataIsPage(bean);
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
    public Result selectList(WechatUserInfo bean){
        log.info(this.getClass().getName()+"======================selectList");
        Result result = null;
        try {
            List<WechatUserInfo> list = wechatUserInfoService.findDataIsList(bean);
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
    public Result selectById(WechatUserInfo bean){
        log.info(this.getClass().getName()+"======================selectById");
        Result result = null;
        if (null != bean && Validator.notEmpty(bean.getId())){
            try {
                WechatUserInfo rsBean = wechatUserInfoService.findDataById(bean);
                result = Result.success("查询成功！",rsBean);
            } catch (Exception e) {
                result = Result.sysError(e);
            }
        }
        else{
            result = Result.paramError("参数为空！");
        }
        return result;
    }

}
