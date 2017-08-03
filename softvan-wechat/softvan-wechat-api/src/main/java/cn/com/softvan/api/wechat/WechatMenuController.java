/*
 * @ClassName WechatMenuController
 * @Description
 * @version 1.0
 * @Date 2017/7/6 4:57:5
 */
package cn.com.softvan.api.wechat;

import cn.com.softvan.utils.*;
import cn.com.softvan.api.BaseController;
import cn.com.softvan.model.LoginUser;
import cn.com.softvan.model.wechat.WechatMenu;
import cn.com.softvan.service.wechat.IWechatMenuService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/h/wechat/wechatMenu")
@Slf4j
public class WechatMenuController extends BaseController {
    @Autowired
    private IWechatMenuService wechatMenuService;
    @Autowired
    private WxMpService wxService;

    /**
     * 新增
     * @param bean
     * @return
     */
    @RequestMapping(value = "/add",method= RequestMethod.POST)
    public Result add(@RequestBody WechatMenu bean){
        log.info(this.getClass().getName()+"======================add");
        Result result = null;
        if(null != bean){
            /*if(Validator.isEmpty(bean.getId())){
                bean.setId(IdUtil.createUUID(32));
            }*/
            try {
                if("0".equals(bean.getParent())){
                    //检查父级菜单数量
                    WechatMenu menu = new WechatMenu();
                    menu.setParent("0");
                    List<WechatMenu> wechatMenus = wechatMenuService.findDataIsList(menu);
                    if(wechatMenus!= null && wechatMenus.size()>=3){
                        result = new Result(ResultCode.PARAMS_INVALID, "一级菜单不允许超过3个！");
                        return result;
                    }
                }
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setCreateId(user.getId());
                bean.setUpdateId(user.getId());
                bean.setCreateIp(user.getIp());
                bean.setUpdateIp(user.getIp());
                wechatMenuService.save(bean);
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
    public Result delete(WechatMenu bean){
        log.info(this.getClass().getName()+"======================delete");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                wechatMenuService.deleteById(bean);
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
    public Result deleteLogic(WechatMenu bean){
        log.info(this.getClass().getName()+"======================deleteLogic");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setUpdateId(user.getId());
                bean.setUpdateIp(user.getIp());
                wechatMenuService.update(bean);
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
    public Result update(@RequestBody WechatMenu bean){
        log.info(this.getClass().getName()+"======================update");
        Result result = null;
        if(null != bean && Validator.notEmpty(bean.getId())){
            try {
                LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
                bean.setUpdateId(user.getId());
                bean.setUpdateIp(user.getIp());
                wechatMenuService.update(bean);
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
    public Result selectPage(WechatMenu bean){
        log.info(this.getClass().getName()+"======================selectPage");
        Result result = null;
        try {
            List<WechatMenu> list = wechatMenuService.findDataIsPage(bean);
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
    public Result selectList(WechatMenu bean){
        log.info(this.getClass().getName()+"======================selectList");
        Result result = null;
        try {
            List<WechatMenu> list = wechatMenuService.findDataIsList(bean);
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
    public Result selectById(WechatMenu bean){
        log.info(this.getClass().getName()+"======================selectById");
        Result result = null;
        if (null != bean && Validator.notEmpty(bean.getId())){
            try {
                WechatMenu rsBean = wechatMenuService.findDataById(bean);
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


    /**
     * 发布菜单
     * @return
     */
    @RequestMapping(value = "/pushMenu", method = RequestMethod.PUT)
    public Result pushMenu(){
        Result result;
        //查询菜单
        List<WechatMenu> menus = wechatMenuService.findDataIsList(new WechatMenu());
        List<WxMenuButton> menuButtons = new ArrayList<>();
        if(menus != null && !menus.isEmpty()){
            for(WechatMenu menu: menus){
                if("0".equals(menu.getParent())){
                    WxMenuButton parent = new WxMenuButton();
                    parent.setType(menu.getType());
                    parent.setName(menu.getName());
                    parent.setKey(menu.getKey());
                    parent.setUrl(menu.getUrl());
                    parent.setMediaId(menu.getMediaId());
                    parent.setAppId(menu.getAppid());
                    parent.setPagePath(menu.getPagepath());
                    //为父级菜单,查找子菜单
                    List<WxMenuButton> sub = getChildren(menu, menus);
                    if(!sub.isEmpty()){
                        parent.setSubButtons(sub);
                    }
                    menuButtons.add(parent);
                }
            }
        }

        WxMenu wxMenu = new WxMenu();
        wxMenu.setButtons(menuButtons);
        try {
            wxService.getMenuService().menuCreate(wxMenu);
            result = Result.success("创建成功");
        } catch (WxErrorException e) {
            e.printStackTrace();
            result = new Result(ResultCode.SYS_ERROR, "创建菜单出错！");
        }
        return result;
    }


    private List<WxMenuButton> getChildren(WechatMenu parent, List<WechatMenu> menus){
        List<WxMenuButton> wxMenuButtons = new ArrayList<>();
        for(WechatMenu menu: menus){
            if(parent.getId().equals(menu.getParent())){
                WxMenuButton wxMenuButton = new WxMenuButton();
                wxMenuButton.setType(menu.getType());
                wxMenuButton.setName(menu.getName());
                wxMenuButton.setKey(menu.getKey());
                wxMenuButton.setUrl(menu.getUrl());
                wxMenuButton.setMediaId(menu.getMediaId());
                wxMenuButton.setAppId(menu.getAppid());
                wxMenuButton.setPagePath(menu.getPagepath());
                wxMenuButtons.add(wxMenuButton);
            }
        }

        return wxMenuButtons;
    }


}
