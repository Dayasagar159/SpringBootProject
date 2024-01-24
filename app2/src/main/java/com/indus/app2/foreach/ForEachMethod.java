package com.indus.app2.foreach;

import java.util.Arrays;
import java.util.List;

public class ForEachMethod {

	public static void main(String[] args) 
	{
		List<String> names = Arrays.asList("Sagar", "Priyanka", "Pradnya", "Manish");
		names.stream().forEach(name -> System.out.println(name));

	}

}
