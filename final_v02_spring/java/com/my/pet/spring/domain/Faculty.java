package com.my.pet.spring.domain;

/**
 * 
 * @author peter
 * Faculty entity
 *
 */
public class Faculty {

    private int id;
    private String name;
    private int budgetPlaces;
    private int totalPlaces;

    public Faculty() {
        // empty
    }

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty(int id, String name, int budgetPlaces, int totalPlaces) {
        this.id = id;
        this.name = name;
        this.budgetPlaces = budgetPlaces;
        this.totalPlaces = totalPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
