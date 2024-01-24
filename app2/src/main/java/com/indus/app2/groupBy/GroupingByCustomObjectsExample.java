package com.indus.app2.groupBy;
import java.util.*;
import java.util.stream.Collectors;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class GroupingByCustomObjectsExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 30),
            new Person("David", 25)
        );

        Map<Integer, List<Person>> groupedPeopleByAge = people.stream()
            .collect(Collectors.groupingBy(Person::getAge));

        System.out.println(groupedPeopleByAge);
        
        //please refere below link for clarification
        //https://websparrow.org/java/java-8-collectors-groupingby-method-example
    }
}