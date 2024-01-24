package com.indus.app2.programs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionExample {

	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		Function<Integer, String> intToString = n -> "Number: " + n;
		List<String> numberStrings = map(numbers, intToString);

		System.out.println("Number strings: " + numberStrings);
	}

	public static List<String> map(List<Integer> list, Function<Integer, String> function) {

		List<String> result = new ArrayList<>();
		for (Integer item : list) {
			result.add(function.apply(item));
		}
		return result;
	}
}