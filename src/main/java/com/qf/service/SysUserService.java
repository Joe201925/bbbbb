package com.qf.service;

import com.qf.bean.SysPermission;
import com.qf.bean.SysUser;

import java.util.List;

public interface SysUserService {


    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser login(String username);

    List<SysPermission> findmenus(String userid);
    List<SysPermission> findpers(String userid);

}
