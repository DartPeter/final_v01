package com.my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		String input = "7\n"
				+ "C 1.1 8.15.1 P 15.10.2012 83\n"
				+ "C 1 10.1 P 01.12.2012 65\n"
				+ "C 1.1 5.5.1 P 01.11.2012 117\n"
				+ "D 1.1 8 P 01.01.2012-01.12.2012\n"
				+ "C 3 10.2 N 02.10.2012 100\n"
				+ "D 1 * P 8.10.2012-20.11.2012\n"
				+ "D 3 10 P 01.12.2012";
		BufferedReader reader = new BufferedReader(new StringReader(input));
		reader.readLine();
		String line;
		List<WaitingTimeline> list = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("C")) {
				WaitingTimeline wtl = new WaitingTimeline(line);
				list.add(wtl);
			} else if (line.startsWith("D")) {
				QueryLine ql = new QueryLine(line);
				int avg = (int) (list.stream().filter(ql.filter()).mapToInt(WaitingTimeline::getTime).average().orElse(0));
				System.out.println(avg == 0 ? "-" : avg);
			} else {
				throw new IllegalArgumentException("Wrong input data");
			}
		}
		
	}

}
