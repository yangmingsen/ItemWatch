package com.auction.model;

import java.util.List;

import com.auction.model.dao.ItemDao;
import com.auction.model.entity.ItemEnity;

public class TestConnect {
	public static void main(String[] args) {
		List<ItemEnity> findAll = ItemDao.getInstance().findAll();
		for (ItemEnity item : findAll) {
			System.out.println(item.toString());
		}

	}
}
