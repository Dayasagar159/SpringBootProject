package com.indus.app2.groupBy;
import java.util.*;
import java.util.stream.Collectors;

public class GroupingByWithDownstreamExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 30),
            new Person("David", 25),
            new Person("Daya", 25)
        );

        // Grouping by age and counting the number of people in each group
        Map<Integer, Long> groupedPeopleByAge = people.stream()
            .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));

        System.out.println(groupedPeopleByAge);
    }
}