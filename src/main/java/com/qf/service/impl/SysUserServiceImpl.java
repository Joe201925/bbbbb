package com.qf.service.impl;

import com.qf.bean.SysPermission;
import com.qf.bean.SysUser;
import com.qf.dao.SysUserMapper;
import com.qf.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(SysUser record) {
        return 0;
    }

    @Override
    public int insertSelective(SysUser record) {
        return 0;
    }

    @Override
    public SysUser selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return 0;
    }

    /**
     * 登录功能
     * @param username
     * @return
     */
    @Override
    public SysUser login(String username) {
        SysUser user =  sysUserMapper.login(username);
//        String str = new MD5().getMD5ofStr(password);

        /*if (user!=null&&str.equals(user.getPassword())){
            List<SysPermission> findpers = sysUserMapper.findpers(user.getId());
            List<SysPermission> findmenus = sysUserMapper.findmenus(user.getId());
            user.setMenus(findmenus);
            user.setPermissionList(findpers);*/
            return user;
    }

    @Override
    public List<SysPermission> findmenus(String userid) {

        return sysUserMapper.findmenus(userid);
    }

    @Override
    public List<SysPermission> findpers(String userid) {
        return
                sysUserMapper.findpers(userid);
    }
}
