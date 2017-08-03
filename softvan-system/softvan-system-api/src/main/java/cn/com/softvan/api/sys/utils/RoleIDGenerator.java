package cn.com.softvan.api.sys.utils;

import cn.com.softvan.model.sys.SysRole;
import cn.com.softvan.service.sys.ISysRoleService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/07/03
 * Time: 16:41
 */
@Log4j
@Component
public class RoleIDGenerator {
    /**
     * ID保存队列
     */
    private static final Queue<String> ids = new LinkedList<>();
    /**
     * 角色管理
     **/
    @Autowired
    private ISysRoleService sysRoleService;
    /**
     * 随机序列
     */
    public static final String allChar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random random = new Random();

    /**
     * 生成随机短id，给角色使用
     * @return
     */
    public synchronized String getId() {
        String id = ids.poll();
        return id==null?initIds():id;
    }

    /**
     * 生成id并加入队列
     *
     * @return
     */
    private synchronized String initIds() {
        long startTime = System.currentTimeMillis();
        List<SysRole> roles = sysRoleService.findDataIsList(new SysRole());
        Set<String> tempId = new HashSet<>();
        Set<String> existId = new HashSet<>();
        if (roles != null && !roles.isEmpty()) {
            for (SysRole sysRole : roles) {
                existId.add(sysRole.getId());
            }
            //生成id
            for(int i=0;i<1000;i++){
            //while (tempId.size()<1000){
                String id = generateString(random.nextInt(3));
                if(!existId.contains(id)){
                    tempId.add(id);
                }
            }

            for(String tid: tempId){
                ids.offer(tid);
            }
            long endTime = System.currentTimeMillis();
            log.info("生成ID共："+tempId.size()+"; 耗时："+(endTime-startTime)+"ms");
        }
        return ids.poll();
    }


    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    private static String generateString(int length) {
        if(length == 0)length=3;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

}
