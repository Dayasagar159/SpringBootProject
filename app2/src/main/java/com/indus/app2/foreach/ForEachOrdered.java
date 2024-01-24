package com.indus.app2.foreach;

import java.util.Arrays;
import java.util.List;

public class ForEachOrdered {

	public static void main(String[] args) 
	{
		
		List<String> names = Arrays.asList("Sagar", "Priyanka", "Pradnya", "Manish");
		names.stream().forEachOrdered(name -> System.out.println(name));

	}

}
