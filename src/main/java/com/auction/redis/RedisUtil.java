package com.auction.redis;

import java.util.List;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisUtil {
	
	//服务器IP地址
	private static String ADDR = "192.168.91.129";
	
	//端口
	private static int PORT = 6379;
	//密码
	private static String AUTH = "123456";

	//连接实例的最大连接数
	private static int MAX_ACTIVE = 1024;

	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
	private static int MAX_WAIT = 3000;

	//连接超时的时间　　
	private static int TIMEOUT = 10000;
	
	private static JedisPool jedisPool = null;
	
	private static Jedis jedis = null;

	/**
	 * 初始化Redis连接池
	 */

	static {
	    try {
	        JedisPoolConfig config = new JedisPoolConfig();
	        config.setMaxTotal(MAX_ACTIVE);
	        config.setMaxIdle(MAX_IDLE);
	        config.setMaxWaitMillis(MAX_WAIT);

	        jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, 
	        		AUTH, 0, null);

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	}

	/**
	 * 获取Jedis实例
	 */

	public synchronized static Jedis getJedis() {

	    try {

	        if (jedisPool != null) {
	            Jedis resource = jedisPool.getResource();
	            return resource;
	        } else {
	            return null;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }

	}
	/***
	 *
	 * 释放资源
	 */

	public static void returnResource(final Jedis jedis) {
	    if(jedis != null) {
	        jedisPool.returnResource(jedis);
	    }

	}
}
