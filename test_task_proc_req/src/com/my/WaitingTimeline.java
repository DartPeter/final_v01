package com.my;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WaitingTimeline extends Record {
	
	private static final int N = 6;

	private LocalDate date;
	private Integer time;

	public WaitingTimeline(String line) {
		super(line);
	}

	@Override
	protected void checkLength(String[] strings) {
		if (strings.length < N) {
			throw new IllegalArgumentException("Wrong string format need more tokens.");
		}
	}
	
	protected void checkType(String s) {
		if (!("C").equals(s)) {
			throw new IllegalArgumentException("Must start with C");
		}
	}
	
	protected void setTale(String[] strings) {
		date = LocalDate.parse(strings[4], DateTimeFormatter.ofPattern("d.M.y"));
		time = Integer.parseInt(strings[5]);
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getTime() {
		return time;
	}

}
