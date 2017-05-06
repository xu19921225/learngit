package com.itheima.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * ����һ����������Dao�Ĺ�����
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
	
	//����DAO,�õ�������ͨ����
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
	
	//����DAO,�õ����Ǵ������
	public Object getProxyDao(String className) {
		try {
			String classPath = bundle.getString(className);
			final Object obj =  Class.forName(classPath).newInstance();
			//���ɴ������
			Object proxyObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), 
							new InvocationHandler() {
								public Object invoke(Object proxy, Method method, Object[] args)
										throws Throwable {
									//������ʲô,��ִ�з���֮ǰ��������
									return method.invoke(obj,args);
									//������ʲô,��������ִ�з���֮����
								}
							});
			
			return proxyObj;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
