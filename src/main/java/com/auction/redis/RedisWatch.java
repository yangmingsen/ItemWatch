package com.auction.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

/***
 * 使用说明:
 * <br>
 *  1.使用前请务必<strong>先调用configNotifyKeyspaceEvents</strong> method进行Events开启
 * <br>
 *  2.确保第一步操作完毕后,便可使用addEvents method
 *  
 * @author yangmingsen
 *
 */
public class RedisWatch {

	private static void config(Jedis jedis) {
		String parameter = "notify-keyspace-events";
		List<String> notify = jedis.configGet(parameter);
		if (notify.get(1).equals("")) {
			jedis.configSet(parameter, "Ex"); // 过期事件
		}
	}

	public static boolean configNotifyKeyspaceEvents() {
		Jedis jedis = RedisUtil.getJedis();
		try {
			config(jedis);
			jedis.psubscribe(new RedisKeyExpiredListener(), "__keyevent@0__:expired");// 过期队列

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean addEvents(String key, int seconds, String value) {
		Jedis jedis = RedisUtil.getJedis();
		
		try {
			jedis.setex(key, seconds, value);
			
			return true;
		}catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
			// TODO: handle exception
		}
		
	}

}
