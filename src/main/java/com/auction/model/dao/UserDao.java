package com.auction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.auction.model.JdbcUtil;

public class UserDao {
	private static Connection conn = null;
	private static UserDao userDaoSingle = null;

	private UserDao() {
		try {
			conn = JdbcUtil.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加synchronized同步锁或是用同步代码块对类加同步锁. 使用双重检查进一步做了优化，可以
	 * 避免整个方法被锁，只对需要锁的代码部分加锁，可以提高执行效率
	 * 
	 * @return
	 */
	public static UserDao getInstance() {
		if (userDaoSingle == null) {
			synchronized (UserDao.class) {
				if (userDaoSingle == null) {
					userDaoSingle = new UserDao();
				}
			}
		}

		return userDaoSingle;
	}
	
	public String findEmailAddrByUsername(String username) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT email FROM as_user WHERE username=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("email");
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
