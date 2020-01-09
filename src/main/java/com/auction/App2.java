package com.auction;

import com.auction.listener.MessageListener;
import com.auction.queue.MessageQueue;
import com.auction.redis.RedisWatch;

public class App2 {
	public static void main(String[] args) throws Exception {
		String str = "416-yangmingsen";

		// 启动MessageListener
		Thread thread = new Thread(new MessageListener());
		thread.start();
		
		Thread.sleep(1000*3);
		
		MessageQueue.putMessage(str);

	}
}
