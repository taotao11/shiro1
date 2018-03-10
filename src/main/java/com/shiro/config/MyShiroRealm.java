package com.shiro.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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
        System.out.println("------::---" + name);

        //判断用户 用户不存在则抛 UnknownAccountException
        if ("aaa".equals(name)){
            throw new UnknownAccountException("用户不存在");
        }
        if ("look".equals(name)){
            throw new LockedAccountException("用户被锁的");
        }

        //principal 认证的对象信息 可以是name,也可以是对象的实体信息
        Object principal = name;

        // 密码
        Object credentials = null;

        if (name.equals("admin")){
           credentials = "a66abb5684c45962d887564f08346e8d";
        }else if (name.equals("user")){
            credentials = "123456";
        }


        //当前reaml对象的name,调用父类的方法
        String realmName = getName();


        /***
         * SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmName);
         * 明文比对 SimpleAuthenticationInfo(principal,credentials,realmName);
         * 加密比对SimpleAuthenticationInfo(principal, //用户名
         * credentials,//密码
         * ByteSource.Util.bytes(userInfo.getCredentialsSalt()),
         * salt=username+salt getName()  //realm name);
         */

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                principal, //用户名
                credentials, //密码
                ByteSource.Util.bytes(getName()) ,//salt=username+salt 盐值
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
