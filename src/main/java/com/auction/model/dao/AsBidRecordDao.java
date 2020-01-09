package com.auction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.auction.model.JdbcUtil;
import com.auction.model.entity.BidRecordEntity;
import com.auction.model.entity.ItemEnity;

public class AsBidRecordDao {
	private static Connection conn = null;
	private static AsBidRecordDao asBidRecordDaoSingle = null;

	private AsBidRecordDao() {
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
	public static AsBidRecordDao getInstance() {
		if (asBidRecordDaoSingle == null) {
			synchronized (AsBidRecordDao.class) {
				if (asBidRecordDaoSingle == null) {
					asBidRecordDaoSingle = new AsBidRecordDao();
				}
			}
		}

		return asBidRecordDaoSingle;
	}

	public BidRecordEntity findMaxBidPriceByItemId(long itemId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM as_bid_record WHERE auction_item_id=? ORDER BY bid_price DESC LIMIT 1";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, itemId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				return new BidRecordEntity(
						rs.getLong("auction_item_id"),
						rs.getString("buyer_id"),
						rs.getDouble("bid_price"));

			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;

	}

}
