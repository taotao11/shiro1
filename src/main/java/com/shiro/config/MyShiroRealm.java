package com.shiro.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyShiroRealm extends AuthorizingRealm {
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       System.out.println("----:  " + authenticationToken);
       //强转类型
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获得名称
        String name = token.getUsername();
        System.out.println(name);

        //判断用户 用户不存在则抛 UnknownAccountException
        if ("aaa".equals(name)){
            throw new UnknownAccountException("用户不存在");
        }
        if ("look".equals(name)){
            throw new LockedAccountException("用户被锁的");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        return null;
    }
}
