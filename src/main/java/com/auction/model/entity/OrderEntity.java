package com.auction.model.entity;

public class OrderEntity {
	private long itemId;
	private Double transactionPrice;
	private String sellerId;
	private String buyerId;
	private String transactionTime;
	private String status;
	private String expirationTime;
	

	public OrderEntity(long itemId, Double transactionPrice, String sellerId, String buyerId, String transactionTime,
			String status, String expirationTime) {
		super();
		this.itemId = itemId;
		this.transactionPrice = transactionPrice;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.transactionTime = transactionTime;
		this.status = status;
		this.expirationTime = expirationTime;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public Double getTransactionPrice() {
		return transactionPrice;
	}
	public void setTransactionPrice(Double transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}
	@Override
	public String toString() {
		return "OrderEntity [itemId=" + itemId + ", transactionPrice=" + transactionPrice + ", sellerId=" + sellerId
				+ ", buyerId=" + buyerId + ", transactionTime=" + transactionTime + ", status=" + status
				+ ", expirationTime=" + expirationTime + "]";
	}
	
}
