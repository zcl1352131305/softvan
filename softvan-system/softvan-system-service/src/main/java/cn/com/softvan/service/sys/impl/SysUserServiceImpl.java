package cn.com.softvan.service.sys.impl;

import cn.com.softvan.dao.sys.SysRoleMapper;
import cn.com.softvan.dao.sys.SysUserMapper;
import cn.com.softvan.dao.sys.SysUserRoleMapper;
import cn.com.softvan.model.sys.SysRole;
import cn.com.softvan.model.sys.SysUser;
import cn.com.softvan.model.sys.SysUserRole;
import cn.com.softvan.service.file.ICommonFileService;
import cn.com.softvan.service.sys.ISysUserService;
import cn.com.softvan.utils.IdUtil;
import cn.com.softvan.utils.Validator;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vicqiang on 2017/6/1 0001.
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private ICommonFileService commonFileService;

    public SysUser findDataById(SysUser bean) {
        SysUser user = sysUserMapper.selectByPrimaryKey(bean.getId());
        if(Validator.notEmpty(bean.getId())){
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(bean.getId());
            user.setRoles(sysRoleMapper.selectUserRole(userRole));
            user.setHeadImgFile(commonFileService.getFiles(bean.getId(),"headImg"));
        }
        return user;
    }

    public int deleteById(SysUser bean) throws Exception{
        return sysUserMapper.deleteByPrimaryKey(bean.getId());
    }

    public int save(SysUser bean) throws Exception{
        SysUser bean1 = sysUserMapper.selectByPrimaryKey(bean.getId());
        if(null != bean1){
            return 1;
        }
        else{
            if(null != bean.getRoles() && bean.getRoles().size() > 0){
                for(SysRole role:bean.getRoles()){
                    if(Validator.notEmpty(role.getId())){
                        SysUserRole userRole = new SysUserRole();
                        userRole.setId(IdUtil.createUUID(32));
                        userRole.setRoleId(role.getId());
                        userRole.setUserId(bean.getId());
                        sysUserRoleMapper.insert(userRole);
                    }
                }
            }
            if(null !=  bean.getHeadImgFile()){
                commonFileService.saveFile(bean.getId(),"headImg",bean.getHeadImgFile());
            }
        }


        return sysUserMapper.insert(bean);
    }

    public int update(SysUser bean) throws Exception{
        if(null != bean.getRoles() && bean.getRoles().size() > 0){
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(bean.getId());
            sysUserRoleMapper.deleteSelective(userRole);
            for(SysRole role:bean.getRoles()){
                if(Validator.notEmpty(role.getId())){
                    userRole = new SysUserRole();
                    userRole.setId(IdUtil.createUUID(32));
                    userRole.setRoleId(role.getId());
                    userRole.setUserId(bean.getId());
                    sysUserRoleMapper.insert(userRole);
                }
            }
            if(null !=  bean.getHeadImgFile()){
                commonFileService.saveFile(bean.getId(),"headImg",bean.getHeadImgFile());
            }
        }
        return sysUserMapper.updateByPrimaryKeySelective(bean);
    }

    public List<SysUser> findDataIsPage(SysUser bean) {
        if(bean.getPage() != null && bean.getRows() != null){
            PageHelper.startPage(bean.getPage(),bean.getRows());
        }
        return sysUserMapper.selectList(bean);
    }

    public List<SysUser> findDataIsList(SysUser bean){
        return (List<SysUser>) sysUserMapper.selectList(bean);
    }

    public SysUser findDataByUserName(SysUser bean) {
        return sysUserMapper.selectByUserName(bean);
    }




}
