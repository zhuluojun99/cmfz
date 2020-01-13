package com.baizhi.shiro;

import com.baizhi.entity.Admin;
import com.baizhi.entity.PermDto;
import com.baizhi.entity.RoleDto;
import com.baizhi.service.AdminService;
import com.baizhi.util.ApplicationContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;

public class Realm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //根据主身份查用户
        AdminService admin = (AdminService) ApplicationContextUtil.getbean(AdminService.class);
        Admin querybyname = admin.querybyname(primaryPrincipal);
        //根据用户查询其对应的角色
        HashSet<String> set = new HashSet<>();
        List<RoleDto> roleDtos = admin.queryByid(querybyname.getId());
        for (RoleDto roleDto : roleDtos) {
            authorizationInfo.addRole(roleDto.getName());
            //根据角色查询权限
            List<PermDto> permDtos = admin.queryByuserid(roleDto.getId());
            for (PermDto per : permDtos) {
                set.add(per.getPerms());
            }
        }
        for (String s : set) {
            authorizationInfo.addStringPermission(s);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //先获得到主体
        String principal = (String) authenticationToken.getPrincipal();
        AdminService admin = (AdminService) ApplicationContextUtil.getbean(AdminService.class);
        //查询到是否由此用户
        Admin querybyname = admin.querybyname(principal);
        AuthenticationInfo authenticationInfo = null;
        if (querybyname != null) {
            authenticationInfo = new SimpleAuthenticationInfo(querybyname.getUsername(), querybyname.getPassword(), this.getName());
        }
        return authenticationInfo;
    }
}
