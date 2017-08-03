package cn.com.softvan.model.sys;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/04
 * Time: 17:13
 * 页面菜单bean
 */
@Data
public class SysMenu {

    public SysMenu(){
        this.child = new ArrayList<>();
    }

    public SysMenu(String id, String parent, String title, String icon, String url, String type,String order){
        this.id = id;
        this.parent = parent;
        this.title = title;
        this.icon = icon;
        this.url = url;
        this.type = type;
        this.order = order;
        this.child = new ArrayList<>();
    }

    private String id;
    private String parent;
    private String title;
    private String icon;
    private String url;
    private String type;
    private String order;
    private List<SysMenu> child;

}
