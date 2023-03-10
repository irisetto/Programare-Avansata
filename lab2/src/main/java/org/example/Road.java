package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Road este o clasa folosita pentru crearea drumurilor, care asemanator clasei Location interzice crearea obiectelor ce exista deja.
 * Ce o diferentiaza de clasa Location este ca in aceasta clasa tipul drumului se va defini printr-un enum, nu prin clase mostenite cum am observat mai sus.
 */
public class Road {
    private String name;
    private Roads type;
    private double length;
    private Integer speedLimit;
    private static Set<Integer> existingHashes = new HashSet<>();

    public Road(String name, Roads type, double length, Integer speedLimit) {

        int hash = Objects.hash(name, type, length, speedLimit);
        if (existingHashes.contains(hash)) {
            System.out.println("You cannot create instances that are the same!");
            System.exit(0);
        }
        this.name = name;
        this.type = type;
        this.length = length;

        this.speedLimit = speedLimit;
        existingHashes.add(hash);
    }

    public String getName() {
        return name;
    }

    public Roads getType() {
        return type;
    }

    public double getLength() {
        return length;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Roads type) {
        this.type = type;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setSpeedLimit(Integer speedLimit) {
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return "Road{" +
                "name=\'" + name +
                "\', " +
                "type=" + type +
                ", length=" + length +
                ", speedLimit=" + speedLimit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Road road = (Road) o;
        if (!getName().equals(road.getName())) return false;
        if (getType() != road.getType()) return false;
        if (getLength() != road.getLength()) return false;
        return getSpeedLimit().equals(road.getSpeedLimit());
    }

}
