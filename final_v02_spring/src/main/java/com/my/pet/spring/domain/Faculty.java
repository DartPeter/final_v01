package com.my.pet.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author peter
 * Faculty entity
 *
 */
@Entity
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "budget_places")
    private int budgetPlaces;
    @Column(name = "total_places")
    private int totalPlaces;

    public Faculty() {
        // empty
    }

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty(Integer id, String name, int budgetPlaces, int totalPlaces) {
        this.id = id;
        this.name = name;
        this.budgetPlaces = budgetPlaces;
        this.totalPlaces = totalPlaces;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudgetPlaces() {
        return budgetPlaces;
    }

    public void setBudgetPlaces(int budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", budgetPlaces=" + budgetPlaces + ", totalPlaces="
				+ totalPlaces + "]";
	}
    
    
}
