package com.ors.service.impl;

import com.ors.dao.impl.LoginDaoImpl;
import com.ors.dao.service.LoginDao;
import com.ors.model.Login;
import com.ors.service.services.LoginService;

public class LoginServiceImpl implements LoginService {

	private final LoginDao loginDao;

	public LoginServiceImpl() {
		loginDao = new LoginDaoImpl();
	}

	@Override
	public boolean checkLogin(Login login) {
		boolean result = false;

		LoginDao loginDao = new LoginDaoImpl();
		result = loginDao.checkLogin(login);

		return result;
	}

	@Override
	public int insertLogin(Login login) {
		int result = 0;

		LoginDao loginDao = new LoginDaoImpl();
		result = loginDao.insertLogin(login);

		return result;
	}

	@Override
	public int updatePassword(String userName, String oldPassword, String newPassword) {
		int result = 0;

		LoginDao loginDao = new LoginDaoImpl();
		result = loginDao.updatePassword(userName, oldPassword, newPassword);

		return result;
	}

	@Override
	public String getUserType(String userName) {
		return loginDao.getUserType(userName);
	}

}
