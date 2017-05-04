package com.yjy.realm;


import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.yjy.entity.User;
import com.yjy.service.UserService;

public class MyRealm extends AuthorizingRealm{

	@Resource
	private UserService userService;
	
	/**
	 * 为当前登录的用户授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user=(User)principals.getPrimaryPrincipal();
		System.out.println("用户授权--loginUser：" + user);
		
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.getRoles(user.getUserName()));
		authorizationInfo.setStringPermissions(userService.getPermissions(user.getUserName()));
		return authorizationInfo;
	}

	/**
	 * 认证回调函数，登录信息和用户验证信息验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		User loginUser = tokenToUser((UsernamePasswordToken) token);
		System.out.println("认证登录--loginUser:" + loginUser);
		
		//验证用户是否可以登录
		User user = null;
		user = userService.login(loginUser);
		if(user != null){
			//当前 Realm 的 name
			String realmName = this.getName();
			//登录的主要信息：可以是一个实体类的对象，但该实体类的对象一定是根据token 的username查询得到的
			Object principal = user; //token.getPrincipal();
			return new SimpleAuthenticationInfo(principal, loginUser.getPassword(), realmName);  
		}
		return null;
	}

	private User tokenToUser(UsernamePasswordToken token) {
		User user = new User();
		user.setUserName(token.getUsername());
		user.setPassword(String.valueOf(token.getPassword()));
		return user;
	}

}
