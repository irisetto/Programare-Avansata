package org.example;
/**
 * CityLocation, AirportLocation si GasStationLocation sunt clase ce extind clasa de baza abstracta Location.
 * Acestea au un constructor ce apeleaza constructorul superclasei si, de asemenea, fiecare au si o proprietate
 * specifica tipului de locatie.
 */
public class CityLocation extends Location {
    private long population;

    public CityLocation(String name, Double x, Double y, long population) {
        super(name, "CITY", x, y);
        this.population = population;
    }
}