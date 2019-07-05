package com.ors.service.services;

import com.ors.model.Login;

public interface LoginService {
	public boolean checkLogin(Login login);

	public int insertLogin(Login login);

	public int updatePassword(String userName, String oldPassword, String newPassword);
	
	public String getUserType(String userName);
}
