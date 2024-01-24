package com.indus.app2.groupBy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExample1 {

	public static void main(String[] args) 
	{
		List<String> fruits=Arrays.asList("apple", "banana", "orange", "kiwi", "grape");
		
		Map<Integer, List<String>> groupMap=fruits.stream().collect(Collectors.groupingBy(String:: length));
		System.out.println(groupMap);

	}

}
