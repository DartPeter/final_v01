package com.hiber.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import com.hiber.config.TestHibernateConfiguration;
import com.hiber.entity.BankAccount;
import com.hiber.entity.BillingDetails;
import com.hiber.entity.Buyer;
import com.hiber.entity.CreditCard;

@SpringJUnitConfig(TestHibernateConfiguration.class)
@Transactional
class BuyerDAOImplTest {

	@Autowired
	private BuyerDAO buyerDAO;
	
	@Test
	void testCreateWithInheritance() {
		Buyer buyer = new Buyer();
		buyer.setFirstName("fn1");
		buyer.setLastName("ln1");
		
		BillingDetails billingDetails = new BillingDetails();
		billingDetails.setBuyer(buyer);
		buyer.getBillingDetails().add(billingDetails);
		
		// Inheritance tests
		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccount("acc1");
		bankAccount.setBandName("bn1");
		bankAccount.setBuyer(buyer);
		buyer.getBillingDetails().add(bankAccount);
		
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNumber("cn1");
		creditCard.setExpMonth(2);
		creditCard.setExpYear(2222);
		creditCard.setBuyer(buyer);
		buyer.getBillingDetails().add(creditCard);
		
		buyerDAO.save(buyer);
		Assertions.assertNotNull(buyer.getId());
		
		Buyer buyerReaded = buyerDAO.get(buyer.getId());
		List<BillingDetails> billingDetailsList = buyerDAO.getBillingDetails(buyerReaded.getId());
		Assertions.assertEquals(3, billingDetailsList.size());
		Assertions.assertEquals(buyerReaded.getId(), billingDetailsList.get(0).getBuyer().getId());
		
		Integer typeBasePos = null;
		Integer typeBAPos = null;
		Integer typeCCPos = null;
		for(int i = 0; i < billingDetailsList.size(); i++) {
			if (billingDetailsList.get(i) instanceof BankAccount) {
				typeBAPos = i;
			} else if (billingDetailsList.get((int)i) instanceof CreditCard) {
				typeCCPos = i;
			} else {
				typeBasePos = i;
			}
		}
		billingDetails.setId(billingDetailsList.get(typeBasePos).getId());
		bankAccount.setId(billingDetailsList.get(typeBAPos).getId());
		creditCard.setId(billingDetailsList.get(typeCCPos).getId());
		Assertions.assertEquals(billingDetailsList.get((int)typeBasePos), billingDetails);
		Assertions.assertEquals(billingDetailsList.get((int)typeBAPos), bankAccount);
		Assertions.assertEquals(billingDetailsList.get((int)typeCCPos), creditCard);
	}

}
