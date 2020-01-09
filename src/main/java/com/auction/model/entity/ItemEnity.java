package com.auction.model.entity;

public class ItemEnity {
	private long id;
	private String sellerId;
	private String endTime;
	public ItemEnity(long id, String endTime) {
		super();
		this.id = id;
		this.endTime = endTime;
	}
	
	public ItemEnity(long id, String sellerId, String endTime) {
		super();
		this.id = id;
		this.endTime = endTime;
		this.sellerId = sellerId;
	}
	
	
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "ItemEnity [id=" + id + ", sellerId=" + sellerId + ", endTime=" + endTime + "]";
	}
	
}
