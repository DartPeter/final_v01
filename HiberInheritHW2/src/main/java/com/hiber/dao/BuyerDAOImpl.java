package com.hiber.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hiber.entity.BillingDetails;
import com.hiber.entity.Buyer;

@Repository
public class BuyerDAOImpl implements BuyerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;	

    @Transactional
	public void save(Buyer buyer) {
		sessionFactory.getCurrentSession().save(buyer);
	}

	public Buyer get(Long buyerId) {
		Buyer buyer = sessionFactory.getCurrentSession().get(Buyer.class, buyerId);
		return Objects.requireNonNull(buyer, "Buyer not found by id: " + buyerId);
	}

	@Override
	public List<BillingDetails> getBillingDetails(Long buyerId) {
		return get(buyerId).getBillingDetails();
	}

}
