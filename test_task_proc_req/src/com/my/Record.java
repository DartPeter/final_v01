package com.my;

public abstract class Record {
	
	protected Integer serviceId;
	protected Integer variationId;
	
	protected Integer questionTypeId;
	protected Integer categoryId;
	protected Integer subCategoryId;
	
	protected String letter;
	
    protected Record(String line) {
		String[] fields = line.split("\s+");
		checkLength(fields);
		checkType(fields[0]);
		setService(fields[1]);
		setQuestion(fields[2]);
		setLetter(fields[3]);
		setTale(fields);
	}
	
	protected abstract void checkLength(String[] strings);
	
	protected abstract void checkType(String s);
	
	protected void setService(String s) {
		s = s.trim();
		String[] group1 = s.split("\\.");
		if (group1.length > 2) {
			throw new IllegalArgumentException("Service info must contains at most one separator.");
		}
		serviceId = Integer.parseInt(group1[0]);
		if (group1.length == 2) {
			variationId = Integer.parseInt(group1[1]);
		}
	}
	
	protected void setQuestion(String s) {
		String[] group2 = s.split("\\.");
		if (group2.length > 3) {
			throw new IllegalArgumentException("Question info must contains at most two separators.");
		}
		questionTypeId = Integer.parseInt(group2[0]);
		if (group2.length >= 2) {
			categoryId = Integer.parseInt(group2[1]);
			if (group2.length == 3) {
				subCategoryId = Integer.parseInt(group2[2]);
			}
		}
	}
	
	protected void setLetter(String s) {
		letter = s;
		if (!(letter.equals("N") || letter.equals("P"))) {
			throw new IllegalArgumentException("Response type must be P or N");
		}
	}
	
	protected abstract void setTale(String[] strings);

	public Integer getServiceId() {
		return serviceId;
	}

	public Integer getVariationId() {
		return variationId;
	}

	public Integer getQuestionTypeId() {
		return questionTypeId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public String getLetter() {
		return letter;
	}
	
}
