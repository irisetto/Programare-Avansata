package org.example;

public class Company implements Comparable<Company>, Node {
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
