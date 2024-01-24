package com.indus.app2.programs;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindDuplicatesUsingMap {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 7, 8, 1,8);

        Map<Integer, Long> elementCountMap = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        elementCountMap.forEach((key, value) -> {
            if (value > 1) {
                System.out.println("Duplicate element: " + key);
            }
        });
    }
}
