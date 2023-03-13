package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Programmer("Teodora", "06.08.2002", "Java");
        Person person2 = new Designer("Olivia", "10.08.2030", "Photoshop");
        Person person3 = new Designer("Amelia", "11.08.2030", "Illustrator");
        Company foodCompany = new Company("Spartan", 2000);

        System.out.println(person1.getName());
        System.out.println(person2.getName());
        person1.addRelationship("boss", person3);
        person1.addRelationship("sister", person2);
        person2.addRelationship("sister", person3);
        // daca scot argumentul de mai jos da eroare ca avem o relatie intre cele doua noduri deja
        person1.addRelationship("hater", foodCompany);
        foodCompany.addRelationship("client", person1);

        Network network = new Network();
        network.addNode(person1);
        network.addNode(person2);
        network.addNode(person3);
        network.addNode(foodCompany);
        System.out.println(person3.numberOfConnections());
        System.out.println(network.importanceOf(person1));
        System.out.println(network.importanceOf(person3));
        System.out.println(network.importanceOf(foodCompany));
        network.print();
    }
}