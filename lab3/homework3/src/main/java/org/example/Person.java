package org.example;

import java.util.HashMap;
import java.util.Map;

public class Person implements Comparable<Person>, Node {
    private String name;
    private String birthDate;
    private Map<String, Node> relationships = new HashMap<>();

    public Person() {

        this.name = "";
    }

    public Person(String name, String birthDate) {

        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public int compareTo(Person o) {

        return this.name.compareTo(o.name);
    }

    public void addRelationship(String relation, Node node) {
        if(relationships.containsValue(node)){
            System.out.println("Exista deja relatie catre acest nod!");
            return;
        }
        relationships.put(relation, node);
        node.getRelationships().put(relation, this);
    }

    public int numberOfConnections() {
        return this.relationships.size();
    }

    @Override
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public Map<String, Node> getRelationships() {
        return relationships;
    }

    @Override
    public String toString() {
        return "Person: " + "name='" + name + '\'' +
                " | " + "birthDate='" + birthDate + '\'' + " | "
                + "numberOfRelationships= " + relationships.size() + '\n';
    }
}
