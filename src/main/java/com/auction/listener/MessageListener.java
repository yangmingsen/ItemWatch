package com.auction.listener;



import java.text.SimpleDateFormat;
import java.util.Date;

import com.auction.model.dao.AsBidRecordDao;
import com.auction.model.dao.ItemDao;
import com.auction.model.dao.OrderDao;
import com.auction.model.entity.BidRecordEntity;
import com.auction.model.entity.OrderEntity;
import com.auction.queue.MessageQueue;
import com.auction.util.DateHelper;

/***
 * 描述：
 * <br>
 * 请使用new Thread 进行启动
 * <br>
 * 该线程用于监听MessageQueue,进行业务操作
 * <br>
 * 请在RedisWatch 开启之后在使用
 * @see com.auction.redis.RedisWatch
 * 
 * @author yangmingsen
 *
 */
public class MessageListener implements Runnable{
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
			
			System.out.println("Aready take message");
			//一直获取队列信息
			String message = MessageQueue.takeMessage();
			
			System.out.println("MessageListener: get message "+message);
			
			//解析message
			String[] splits = message.split("-");
			Long itemId = Long.parseLong(splits[0]);
			String sellerId = splits[1];
			
			
			/***
			 * 
			 * 业务操作
			 */
			//获取竞价最高的竞价者信息
			BidRecordEntity maxBidRecord = AsBidRecordDao.getInstance().findMaxBidPriceByItemId(itemId);
			
			String expirationTime = rollMinute(30);//向后推迟30分钟
			String transactionTime = DateHelper.getYYYY_MM_DD_HH_MM_SS();
			
			OrderEntity orderEntity = new OrderEntity(itemId, maxBidRecord.getBidPrice(), sellerId, maxBidRecord.getBuyerId(), transactionTime, "1", expirationTime);
			
			int x = OrderDao.getInstance().insert(orderEntity);//插入order表
			if(x < 0) {
				System.out.println("insert faild at itemId="+itemId);
			}
			
			//更新itemid的状态 4表示拍卖结束
			//ItemDao.getInstance().updateStatus(itemId, "4");
			
			//发送邮件
			
			
		}
		
	}
	
	/***
	 * 前30分钟 -30
	 * 后30分钟  30
	 * @param minute
	 * @return
	 */
    private String rollMinute(int minute) {
        Date d = new Date();
        Date d2 = new Date(d.getTime() + minute * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStampStr = simpleDateFormat.format(d2);

        return timeStampStr;

    }
	
	
}
