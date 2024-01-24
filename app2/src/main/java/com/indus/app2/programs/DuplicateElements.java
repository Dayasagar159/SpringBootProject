package com.indus.app2.programs;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateElements {

    public static void main(String[] args) {

      
    	// List contains duplicate elements
    	List<Integer> list = Arrays.asList(8, 49, 25, 98, 98, 32, 15, 15,
    								10, 30, 20, 20, 50, 10, 12,	10 
    								);

    	Set<Integer> set = new HashSet<>();
    	Set<Integer> output1 = list.stream()
    								.filter(e -> !set.add(e))
    								.collect(Collectors.toSet());
    			
    	output1.forEach(System.out::println); // 98,20,10,15
    	
    	Set<Integer> set1 = new HashSet<>();
    	
    	for (Integer integer : list) {
			
    		if(set1.add(integer))
    		{
    			System.out.println("unique"+integer);
    		}
    		else
    		{
    			System.out.println("duplicate"+integer);
    		}
		}
    	
    	
    	
}
}