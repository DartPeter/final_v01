package com.hiber.entity;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="billingType", discriminatorType = DiscriminatorType.STRING)
public class BillingDetails {
	@Id
    @GeneratedValue(strategy = SEQUENCE, generator = "billing_seq")
	Long id;
	
	@ManyToOne
	Buyer buyer;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BillingDetails other = (BillingDetails) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
