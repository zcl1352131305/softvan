package cn.com.softvan.api.sys;


import cn.com.softvan.api.BaseController;
import cn.com.softvan.model.LoginUser;
import cn.com.softvan.model.sys.SysMenu;
import cn.com.softvan.model.sys.SysPageElement;
import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.service.sys.ISysPageElementService;
import cn.com.softvan.service.sys.ISysUserService;
import cn.com.softvan.utils.PrivilegeConstant;
import cn.com.softvan.utils.Result;
import cn.com.softvan.utils.ResultCode;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
@RestController
@RequestMapping(value = "/h/home")
@Slf4j
public class HomeController extends BaseController {

    //菜单存储时间
    private final long MENU_SAVE_TIME = 3600;

    /**用户信息管理*/
    @Autowired
    private ISysUserService sysUserService;
    /**页面元素管理*/
    @Autowired
    private ISysPageElementService sysPageElementService;

    @RequestMapping(value = "/index",method= RequestMethod.GET)
    public Result index(){
        log.info(this.getClass().getName()+"======================index");
        LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
        SysUser user1 = new SysUser();
        user1.setId(user.getId());
        List<SysMenu> menus = new ArrayList<>();
        menus = (List<SysMenu>) redisService.get("menuList"+user.getId());
        //if(null == menus || menus.size() <= 0){
            menus = sysPageElementService.findPageElementsByRoleId(user1);
            menus = sortMenu(menus);
            redisService.set("menuList"+user.getId(),menus, MENU_SAVE_TIME);
            return Result.success("初始化成功",menus);
       /* }
        else{
            return Result.success("初始化成功",menus);
        }*/
    }

    private List<SysMenu> sortMenu(List<SysMenu> menus){
        if(menus != null && menus.size() > 0){
            Collections.sort(menus, new Comparator<SysMenu>() {
                @Override
                public int compare(SysMenu o1, SysMenu o2) {
                    if(null == o1.getOrder()) o1.setOrder("1000");
                    if(null == o2.getOrder()) o2.setOrder("1000");
                    return Integer.parseInt(o1.getOrder()) - Integer.parseInt(o2.getOrder());
                }
            });
            for (SysMenu menu:menus) {
                sortMenu(menu.getChild());
            }
        }
        return menus;
    }

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Result getUserInfo(){
        Result result = null;
        LoginUser user = (LoginUser) request.getAttribute(PrivilegeConstant.CONSTANT_USER_INFO);
        SysUser user1 = new SysUser();
        user1.setId(user.getId());
        user1 = sysUserService.findDataById(user1);

        if(user1 != null){
            JSONObject object = new JSONObject();
            object.put("id", user1.getId());
            object.put("realName",user1.getRealName());
            if(null != user1.getHeadImgFile() && user1.getHeadImgFile().size() > 0){
                object.put("avatar",user1.getHeadImgFile().get(0).getFileUrl());
            }
            else{
                object.put("avatar", "");
            }

            result = Result.success("查询成功", object);
        }else{
            result = new Result(ResultCode.NOT_FOUND, "用户不存在");
        }
        return result;
    }

    private JSONArray getChildren(JSONArray jsons,String pid){
        JSONArray jsons1 = new JSONArray();
        JSONArray jsons2 = new JSONArray();
        JSONArray jsons3 = new JSONArray();
        for (int i =0; i<jsons.size();i++) {
            JSONObject json = (JSONObject) jsons.get(i);
            if(json.getString("parent").equals(pid)){
                jsons1.add(json);
            }
            else{
                jsons2.add(json);
            }
        }
        for (int i =0; i<jsons1.size();i++) {
            JSONObject json = (JSONObject) jsons1.get(i);
            json.put("children",getChildren(jsons2,json.getString("id")));
            jsons3.add(json);
        }
        return jsons3;
    }


    private JSONArray toTree(List<SysPageElement> elements){
        JSONArray jsons = new JSONArray();
        for (SysPageElement ele:elements) {
            JSONObject json = (JSONObject) JSONObject.toJSON(ele);
            jsons.add(json);
        }
        return getChildren(jsons,"1");
        /*for (int i =0; i<jsons.size();i++) {
            JSONObject json1 = (JSONObject) jsons.get(i);
            if("1".equals(json1.getString("parent"))){
                jsons1.add(json1);
            }
            for(int j=0;j<jsons.size();j++){
                JSONObject json2 = (JSONObject) jsons.get(j);
                if(json2.getString("parent").equals(json1.getString("id"))){
                    if(null == json1.get("children")){
                        JSONArray children = new JSONArray();
                        children.add(json2);
                        json1.put("children",children);
                    }
                    else{
                        JSONArray children = (JSONArray) json1.get("children");
                        children.add(json2);
                        json1.put("children",children);
                    }
                }
            }
        }*/
    }
}
