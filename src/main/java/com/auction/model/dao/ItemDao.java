package com.auction.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.auction.model.JdbcUtil;
import com.auction.model.entity.ItemEnity;


public class ItemDao {
	private static Connection conn = null;
    private static ItemDao itemDaoSingle = null;

    private ItemDao() {
        try {
            conn = JdbcUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加synchronized同步锁或是用同步代码块对类加同步锁.
     * 使用双重检查进一步做了优化，可以
     * 避免整个方法被锁，只对需要锁的代码部分加锁，可以提高执行效率
     * @return
     */
    public static ItemDao getInstance() {
        if(itemDaoSingle == null) {
            synchronized (ItemDao.class) {
                if(itemDaoSingle == null) {
                	itemDaoSingle = new ItemDao();
                }
            }
        }

        return itemDaoSingle;
    }
    
    public List<ItemEnity> findAll() {
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ItemEnity> list = new ArrayList<ItemEnity>();
 		
		String sql = "SELECT id,seller_id,end_time FROM as_auction_item "
				+ "WHERE status=3";
		
		try {
			pstmt = conn.prepareStatement(sql);
				
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				list.add(new ItemEnity(
						rs.getLong("id"),
						rs.getString("seller_id"),
						rs.getString("end_time")));
				
			}
			
			return list;
		}catch (Exception e) {
			
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
		return null;
    }
    
    public String findNameById(Long id) {
    	PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT name FROM as_auction_item WHERE id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("name");
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
    }
    
    public int updateStatus(Long itemId, String status) {
    	 PreparedStatement pstmt = null;
    	 
    	 String sql = "UPDATE `auction_system`.`as_auction_item` SET `status` = ? WHERE `id` = ?";
    	 
    	 try {
    		 pstmt = conn.prepareStatement(sql);
    		 
    		 pstmt.setString(1, status);
    		 pstmt.setLong(2, itemId);
    		 
    		 int x = pstmt.executeUpdate();
    		 
    		 return x;
    		  
    	 }catch(Exception ex) {
    		 ex.printStackTrace();
    	 }
    	 
    	 return -1;
    }
    
}
