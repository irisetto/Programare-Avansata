package org.example;

import java.util.HashMap;
import java.util.Map;

public class Company implements Comparable<Company>, Node {
    private String name;
    private Integer yearFounded;
    private Map<String, Node> relationships = new HashMap<>();

    public Company() {
        this.name = "";
    }

    public Company(String name, Integer yearFounded) {
        this.name = name;
        this.yearFounded = yearFounded;
    }

    public void addRelationship(String relation, Person p) {
        relationships.put(relation, p);
        p.getRelationships().put(relation, this);
    }

    public int numberOfConnections() {
        return this.relationships.size();
    }

    @Override
    public int compareTo(Company o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public Map<String, Node> getRelationships() {
        return relationships;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    @Override
    public String toString() {
        return "Company: " +
                "name='" + name + '\'' +
                " | " + "yearFounded='" + yearFounded + '\'' + " | "
                + "numberOfRelationships= " + this.getRelationships().size() + '\n';
    }
}
