package org.example;

public class Person implements Comparable<Person>, Node {
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
