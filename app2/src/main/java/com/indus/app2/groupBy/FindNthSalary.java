package com.indus.app2.groupBy;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindNthSalary {

    public static void main(String[] args) {

        // Test Case 1 : Unique Employee List
        List<Employee> empList = Arrays.asList(
                new Employee(101, "Manish", 5000, "IT"),
                new Employee(109, "Atul", 3000, "HR"),
                new Employee(111, "Santosh", 4400, "IT"),
                new Employee(109, "Rupendra", 3200, "FIN")
        );

        Map.Entry<Integer, List<String>> nthHighestSalary = getNthHighestSalary(empList, 2);
        System.out.println("Test Case 1: " + nthHighestSalary);

        // Test Case 2 : Duplicate Employee List
        List<Employee> empList2 = Arrays.asList(
                new Employee(11, "Sagar", 4400, "SALES"),
                new Employee(101, "Manish", 5000, "IT"),
                new Employee(109, "Atul", 3000, "HR"),
                new Employee(166, "Santosh", 4400, "IT"),
                new Employee(109, "Rupendra", 3200, "FIN"),
                new Employee(411, "Priyanka", 4400, "ADMIN")
        );

        Map.Entry<Integer, List<String>> nthHighestSalary2 = getNthHighestSalary(empList2, 3);
        System.out.println("Test Case 2: " + nthHighestSalary2);
    }

    private static Map.Entry<Integer, List<String>> getNthHighestSalary(List<Employee> empList, int nth) {

        if (empList.isEmpty() || nth <= 0 || empList.size() < nth)
            throw new IllegalArgumentException("Please validate your inputs.");

        return empList.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.mapping(Employee::getName, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList())
                .get(nth - 1);
    }
}