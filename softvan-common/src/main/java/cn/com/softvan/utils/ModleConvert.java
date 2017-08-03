package cn.com.softvan.utils;

import cn.com.softvan.model.BaseBean;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by vicqiang on 2017/6/6 0006.
 */
public class ModleConvert {
    public static<T> Object pojo2vo(BaseBean bean, Class<T> clazz){
        JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(bean));
        T t = null;
        t = JSONObject.toJavaObject(obj,clazz);
        return t;
    }

}
