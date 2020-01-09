package com.auction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.auction.listener.MessageListener;
import com.auction.model.dao.ItemDao;
import com.auction.model.entity.ItemEnity;
import com.auction.redis.RedisWatch;

/**
 *<h2>ItemWatch 启动入口</h2>
 * 
 * 描述：
 *
 */
public class App {
	public static void main(String[] args) throws Exception{
		
		//首先启动Redis notify-keyspace-events
		App app = new App();
		StartEvent startEvent = app.new StartEvent();
		startEvent.start();
		
		//暂停3秒
		Thread.sleep(1000*3);
		
		//扫描所有正在处于拍卖状态的拍品
		List<ItemEnity> items = ItemDao.getInstance().findAll();
		for(ItemEnity item : items) {
			String message = item.getId()+"-"+item.getSellerId();
			
			long startTimeMillis = new Date().getTime();
			long endTimeMillis   = getFixTimeMillis(item.getEndTime());
		
			int seconds = (int)(endTimeMillis/1000 - startTimeMillis/1000);
			RedisWatch.addEvents(message, seconds, "null");
		}
		
		
	
		
		//启动MessageListener
		Thread thread = new Thread(new MessageListener());
		thread.start();
		
	}
	

    private static long getFixTimeMillis(String fixTimeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null; //初始化date
        try {
            date = sdf.parse(fixTimeString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return date.getTime();
    }
	
	
	
	 class StartEvent extends Thread {
		public void run() {
			RedisWatch.configNotifyKeyspaceEvents();
			System.out.println("Redis configNotifyKeyspaceEvents complete!");
			
			while(true) {
				
			}
			
		}
	}
	
	
}
