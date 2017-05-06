package com.itheima.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * 创建一个负责生产Dao的工厂类
 * @author zhy
 *
 */
public class BeanFactory {
	
	private static ResourceBundle bundle ;
	private static BeanFactory factory = new BeanFactory();
	private BeanFactory(){
		
	}
	static{
		bundle = ResourceBundle.getBundle("com.itheima.properties.bean");
	}
	public static BeanFactory newInstance(){
		return factory;
	}
	
	//生产DAO,拿到的是普通对象
	public Object getDao(String className) {
		try {
			Object obj = null;
			String classPath = bundle.getString(className);
			obj =  Class.forName(classPath).newInstance();
			return obj;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	//生产DAO,拿到的是代理对象
	public Object getProxyDao(String className) {
		try {
			String classPath = bundle.getString(className);
			final Object obj =  Class.forName(classPath).newInstance();
			//生成代理对象
			Object proxyObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), 
							new InvocationHandler() {
								public Object invoke(Object proxy, Method method, Object[] args)
										throws Throwable {
									//你想做什么,就执行方法之前做就行了
									return method.invoke(obj,args);
									//你想做什么,还可以再执行方法之后做
								}
							});
			
			return proxyObj;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
