package com.auction.redis;

import com.auction.queue.MessageQueue;

import redis.clients.jedis.JedisPubSub;

public class RedisKeyExpiredListener extends JedisPubSub {

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		System.out.println("onPSubscribe " + pattern + "-" + subscribedChannels);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println("onPMessage pattern " + pattern + "-" + channel + "-" + message);
		
		MessageQueue.putMessage(message);//增加message 到MessageQueue
		
	}

}
