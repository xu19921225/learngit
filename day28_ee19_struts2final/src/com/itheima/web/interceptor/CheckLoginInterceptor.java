package com.itheima.web.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * ����¼��������
 * @author zhy
 *
 */
public class CheckLoginInterceptor extends MethodFilterInterceptor {

	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//1.��ȡsession����
		HttpSession session = ServletActionContext.getRequest().getSession();
		//2.��session������user����
		User user = (User)session.getAttribute("user");
		//3.û�� ǰ����¼ҳ��
		if(user == null){
			return "login";
		}
		//4.�� ����
		return invocation.invoke();
	}

}
