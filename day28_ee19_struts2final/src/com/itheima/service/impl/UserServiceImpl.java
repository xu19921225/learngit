package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	private IUserDao dao = (IUserDao)BeanFactory.newInstance().getDao("USERDAO");
	//private IUserDao dao = new UserDaoImplForOracle();
	
	public User login(String logonName, String logonPwd) {
		return dao.selectUserByInfo(logonName,logonPwd);
	}

	
	public int saveUser(User user) {
		return dao.addUser(user);
	}

	
	public int modifyUser(User user) {
		return dao.updateUser(user);
	}

	
	public int removeUser(Integer userID) {
		return dao.deleteUser(userID);
	}

	
	public User findUserById(Integer userID) {
		return dao.selectUserById(userID);
	}

	
	public List<User> findAllUser() {
		return dao.selectAllUser();
	}

	
	public List<User> findUserByCondition(String userName, String gender,
			String education, String isUpload) {
		return dao.selectUserByCondition(userName,gender,education,isUpload);
	}

}
