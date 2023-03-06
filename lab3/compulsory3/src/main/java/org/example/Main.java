package org.example;

import java.util.ArrayList;
import java.util.List;

interface Node {
    String getName();
}

class Person implements Comparable<Person>, Node {
    private String name;

    public Person() {
        this.name = "";
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person:" +
                "name='" + name + '\'';
    }
}
class Programmer extends Person{

    public Programmer(String name) {
        super(name);
    }
}
class Company implements Comparable<Company>, Node {
    private String name;

    public Company() {
        this.name = "";
    }

    public Company(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company:" +
                "name='" + name + '\'';
    }
}

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