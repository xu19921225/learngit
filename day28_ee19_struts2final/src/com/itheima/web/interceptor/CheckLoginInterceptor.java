package com.itheima.web.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
/**
 * 检查登录的拦截器
 * @author zhy
 *
 */
public class CheckLoginInterceptor extends MethodFilterInterceptor {

	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//1.获取session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//2.在session域中找user对象
		User user = (User)session.getAttribute("user");
		//3.没有 前往登录页面
		if(user == null){
			return "login";
		}
		//4.有 放行
		return invocation.invoke();
	}

}
