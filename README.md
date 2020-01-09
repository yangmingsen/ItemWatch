### 拍品过期监听服务

描述：服务启动时读取所有未过期并正在处于拍卖状态的的拍品，并以id-sellerId的格式存入redis 的key-envents中，当时间过期后，redis回调将id-sellerId加入到MessageQueue共享队列中。

此时处于监听状态的MessageListener发现MessageQueue中有新数据，马上进行业务处理。


### 业务处理
描述：首先根据itemId去数据库中查询到目前竞价最高的竞价者信息；并将信息添加到订单信息表中。并修改item表的status为4。

之后系统发送邮件通知用户竞价成功，请尽快付款。
