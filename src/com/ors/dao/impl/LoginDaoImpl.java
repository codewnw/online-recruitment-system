package com.ors.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ors.dao.service.LoginDao;
import com.ors.dao.util.ConnectionProvider;
import com.ors.model.Login;

public class LoginDaoImpl implements LoginDao {

	@Override
	public boolean checkLogin(Login login) {
		boolean result = false;

		String userName = login.getUserName();
		String password = login.getPassword();

		try (Connection con = ConnectionProvider.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN");

			while (rs.next()) {
				if (userName.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
					result = true;
					login.setUserType(rs.getString(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertLogin(Login login) {
		int insertedRecordCount = 0;

		try (Connection con = ConnectionProvider.getConnection(); Statement stmt = con.createStatement()) {
			insertedRecordCount = stmt.executeUpdate("INSERT INTO LOGIN VALUES('" + login.getUserName() + "', '"
					+ login.getPassword() + "', '" + login.getUserType() + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertedRecordCount;
	}

	@Override
	public int updatePassword(String userName, String oldPassword, String newPassword) {
		int retVal = 0;

		try (Connection con = ConnectionProvider.getConnection(); Statement stmt = con.createStatement()) {
			String updatePasswordQuery = "Update Login set Password='" + newPassword + "'where Username='" + userName
					+ "'and Password='" + oldPassword + "'";
			retVal = stmt.executeUpdate(updatePasswordQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}

	@Override
	public String getUserType(String userName) {
		String retVal = "";

		try (Connection con = ConnectionProvider.getConnection(); Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGIN WHERE USERNAME = '" + userName + "'");

			rs.next();
			return rs.getString(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
}
