package com.auction.redis;

import redis.clients.jedis.Jedis;

public class Test1 {

	private static Jedis jedis;

	/**
	 * 连接redis服务器
	 */
	static {
		//jedis = RedisUtil.getJedis();
	}

	public static void main(String[] args) {
		
		//RedisWatch.configNotifyKeyspaceEvents();
		RedisWatch.addEvents("yms", 7, "yangmingsen");
		//jedis.setex("notify-task-001", 10, "empty");
	}
}
