package com.my;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

public class QueryLine extends Record {
	
	private static final int N = 5;
	
	private LocalDate from;
	private LocalDate to;

	public QueryLine(String line) {
		super(line);
	}

	@Override
	protected void checkLength(String[] strings) {
		if (strings.length < N) {
			throw new IllegalArgumentException("Wrong string format need more tokens.");
		}
	}

	@Override
	protected void checkType(String s) {
		if (!("D").equals(s)) {
			throw new IllegalArgumentException("Must start with D");
		}
	}
	
	@Override
	protected void setService(String s) {
		if (!"*".equals(s)) {
			super.setService(s);
		}
	}
	
	@Override
	protected void setQuestion(String s) {
		if (!"*".equals(s)) {
			super.setQuestion(s);
		}
	}

	@Override
	protected void setTale(String[] strings) {
		String[] strings2 = strings[4].split("-");
		from = LocalDate.parse(strings2[0], DateTimeFormatter.ofPattern("d.M.y"));
		if (strings2.length == 2) {
			to = LocalDate.parse(strings2[1], DateTimeFormatter.ofPattern("d.M.y"));
		}
	}
	
	public Predicate<WaitingTimeline> filter() {
		return t ->  (serviceId == null || t.serviceId.equals(serviceId)) 
				&& (variationId == null || t.variationId == null || variationId.equals(t.variationId))
				&& (questionTypeId == null || t.questionTypeId == null || questionTypeId.equals(t.questionTypeId))
				&& (categoryId == null || t.categoryId == null || categoryId.equals(t.categoryId))
				&& (subCategoryId == null || t.subCategoryId == null || subCategoryId.equals(t.subCategoryId))
				&& (letter.equals(t.letter))
				&& (t.getDate().isAfter(from) || t.getDate().equals(from))
				&& (to == null || t.getDate().isBefore(to) || t.getDate().equals(to));
	}

}
