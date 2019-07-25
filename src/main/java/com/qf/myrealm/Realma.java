package com.qf.myrealm;

import com.qf.bean.SysPermission;
import com.qf.bean.SysUser;
import com.qf.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class Realma extends AuthorizingRealm {

    @Resource
    private SysUserService sysUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.得到用户信息
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();

        //2.查询角色以及菜单
        List<SysPermission> findpers = sysUserService.findpers(user.getId());
        SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();

        //3.将该用户所对应的权限的字符串提取出来
        List perms= new ArrayList();
        for (SysPermission findper : findpers) {
            perms.add(findper.getPercode());
        }

        info.addStringPermissions(perms);
        return info;
    }




    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证开始,,,,,,,,,,,");
        //1.得到用的姓名
        String username =(String)authenticationToken.getPrincipal();
        // 2.调用service 层 方法进行查询
        SysUser user = sysUserService.login(username);
        if (user==null){
            return null;
        }

        //3.验证密码
        SimpleAuthenticationInfo simple=new SimpleAuthenticationInfo(username,user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),"realma");
        return simple;
    }
}
