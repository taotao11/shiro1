package com.shiro.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 自定义凭证匹配器，用于密码次数的控制.
 * ---------------------------------
 * 编码思路（使用缓存记录用户的密码输入次数）：
 * -------
 * （1）注入缓存对象；
 * （2）获取到当前登录的用户账号username;
 * （3）获取当前账号的登录次数，cache<string,AtomicInteger> ,AtomicInteger:是线程安全的，Integer是非线程安全的；
 * （4）判断是否大于3次，大于的话抛出异常信息，否则进行密码校验，返回信息。
 *
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {


}
