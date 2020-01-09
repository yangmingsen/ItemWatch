package com.auction.model.entity;

public class BidRecordEntity {
	private long itemId;
	private String buyerId;
	private Double bidPrice;
	
	public BidRecordEntity(long itemId, String buyerId, Double bidPrice) {
		super();
		this.itemId = itemId;
		this.buyerId = buyerId;
		this.bidPrice = bidPrice;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public Double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	@Override
	public String toString() {
		return "BidRecordEntity [itemId=" + itemId + ", buyerId=" + buyerId + ", bidPrice=" + bidPrice + "]";
	}
}
