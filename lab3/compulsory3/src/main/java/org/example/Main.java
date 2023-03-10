package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("Teodora");
        Person person2 = new Person();
        person2.setName("Olivia");
        Company itCompany = new Company("Cognizant");
        System.out.println(person1.getName());
        System.out.println(person2.getName());
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(person1);
        nodeList.add(person2);
        nodeList.add(itCompany);
        System.out.println(nodeList);
        person2.setName("Teodor");
        System.out.println(person1.compareTo(person2));

    }
}
