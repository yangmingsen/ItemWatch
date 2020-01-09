package com.auction.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
	private static LinkedBlockingQueue<String> messagesQueue = 
			new LinkedBlockingQueue<String>();
	
	/***
	 * private the custructor 
	 */
	private MessageQueue() {};
	
	/***
	 * add message to queue
	 * @param message
	 * @return
	 */
	public static boolean putMessage(String message) {
		try {
			
			System.out.println("putMessage: put => "+message);
			
			messagesQueue.put(message);
			
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/***
	 * get message from queue
	 * @return
	 */
	public static String takeMessage() {
		try {
			String some = messagesQueue.take();
			
			return some;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
}
