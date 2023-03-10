package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Location este o clasa abstracta ce este si clasa de baza pentru crearea locatiilor. In constructor
 * initializam variabilele si cu ajutorul HashSet-ului pentru fiecare combinatie de valori se creeaza o cheie hash,
 * si in caz ca locatia exista deja se afiseaza un mesaj. In aceasta clasa avem getters si setters corespunzatori
 * si doua functii suprascrise, toString care returneaza modul in care se va interpreta obiectul si equals care
 * verifica daca doua obiecte de tip locatie sunt egale.
 */
public abstract class Location {
    private String name;
    private String type;
    private double x;
    private double y;
    private static Set<Integer> existingHashes = new HashSet<>();

    public Location(String name, String type, double x, double y) {

        int hash = Objects.hash(name, type, x, y); //creeaza valoare hash pt fiecare combinatie de aceste variabile
        if (existingHashes.contains(hash)) { //combinatia exista deja in lista de hash-uri
            System.out.println("You cannot create instances that are the same!");
            System.exit(0);
        }
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
        existingHashes.add(hash);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Location location = (Location) o;
        if (!getName().equals(location.getName())) return false;
        if (getType() != location.getType()) return false;
        if (getX() != location.getX()) return false;
        return getY() != location.getY();
    }

}

