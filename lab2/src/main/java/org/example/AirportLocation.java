package org.example;

public class AirportLocation extends Location {
    private int terminals;

    public AirportLocation(String name, Double x, Double y, int terminals) {
        super(name, "AIRPORT", x, y);
        this.terminals = terminals;
    }
}