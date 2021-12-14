package com.my.pet.spring.dto;

public class FacultyDto {
	
    private Integer id;
    private String name;
    private int budgetPlaces;
    private int totalPlaces;
    
    

    public FacultyDto() {
    	// empty;
	}

	public FacultyDto(Integer id, String name, int budgetPlaces, int totalPlaces) {
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
}
