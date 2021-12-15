package com.hiber.dao;

import java.util.List;

import com.hiber.entity.BillingDetails;
import com.hiber.entity.Buyer;

public interface BuyerDAO {

	void save(Buyer buyer);
	Buyer get(Long buyerId);
	List<BillingDetails> getBillingDetails(Long buyerId);
	
}
