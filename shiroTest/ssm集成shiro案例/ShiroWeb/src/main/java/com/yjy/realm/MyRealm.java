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
	 * Ϊ��ǰ��¼���û������ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user=(User)principals.getPrimaryPrincipal();
		System.out.println("�û���Ȩ--loginUser��" + user);
		
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.getRoles(user.getUserName()));
		authorizationInfo.setStringPermissions(userService.getPermissions(user.getUserName()));
		return authorizationInfo;
	}

	/**
	 * ��֤�ص���������¼��Ϣ���û���֤��Ϣ��֤
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		User loginUser = tokenToUser((UsernamePasswordToken) token);
		System.out.println("��֤��¼--loginUser:" + loginUser);
		
		//��֤�û��Ƿ���Ե�¼
		User user = null;
		user = userService.login(loginUser);
		if(user != null){
			//��ǰ Realm �� name
			String realmName = this.getName();
			//��¼����Ҫ��Ϣ��������һ��ʵ����Ķ��󣬵���ʵ����Ķ���һ���Ǹ���token ��username��ѯ�õ���
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
