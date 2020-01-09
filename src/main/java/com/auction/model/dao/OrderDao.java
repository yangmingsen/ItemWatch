package com.auction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.auction.model.JdbcUtil;
import com.auction.model.entity.OrderEntity;

public class OrderDao {
	private static Connection conn = null;
	private static OrderDao orderDaoSingle = null;

	private OrderDao() {
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
	public static OrderDao getInstance() {
		if (orderDaoSingle == null) {
			synchronized (OrderDao.class) {
				if (orderDaoSingle == null) {
					orderDaoSingle = new OrderDao();
				}
			}
		}

		return orderDaoSingle;
	}

	public synchronized int insert(OrderEntity entity) {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO as_order(auction_item_id,transaction_price,"
				+ "seller_id,buyer_id,transaction_time,status,expiration_time)VALUES" + "(?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, entity.getItemId());
			pstmt.setDouble(2, entity.getTransactionPrice());
			pstmt.setString(3, entity.getSellerId());
			pstmt.setString(4, entity.getBuyerId());
			pstmt.setString(5, entity.getTransactionTime());
			pstmt.setString(6, entity.getStatus());
			pstmt.setString(7, entity.getExpirationTime());

			System.out.println("execute");
			int x = pstmt.executeUpdate();
			
			return x;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return -1;
	}

}
