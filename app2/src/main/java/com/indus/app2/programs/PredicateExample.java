package com.indus.app2.programs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {

	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		Predicate<Integer> evenPredicate = n -> n % 2 == 0;
		List<Integer> evenNumbers = filter(numbers, evenPredicate);

		System.out.println("Even numbers: " + evenNumbers);
	}

	public static List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
		List<Integer> result = new ArrayList<>();
		for (Integer item : list) {
			if (predicate.test(item)) {
				result.add(item);
			}
		}
		return result;
	}
}