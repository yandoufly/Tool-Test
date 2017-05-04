package com.yjy.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class MyAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;
		//û�н�ɫ���ƣ���Ȩ�޷���  
		if (rolesArray == null || rolesArray.length == 0) { 
            return true;  
        } 
		
		for (int i = 0; i < rolesArray.length; i++) { 
			//����ǰ�û���rolesArray�е��κ�һ��������Ȩ�޷���  
            if (subject.hasRole(rolesArray[i])) { 
            	System.out.println(rolesArray[i]);
                return true;  
            }  
        } 
		return false;
	}

}
