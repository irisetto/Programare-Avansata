package org.example;

/**
 * Clasa Destination este o clasa ajutatoare in crearea listei de adiacenta a rutelor dintre locatii.
 * Pentru fiecare locatie exista o lista de tipul Destination in care se mentioneaza in ce locatii se pot
 * ajunge, pe ce drum si cu ce lungime.
 */
public class Destination {
    Location location;
    Double length;
    String name;

    public Destination(Location location1, Double length, String name) {
        this.location = location1;
        this.length = length;
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return " to " +
                location.getName() +
                " through road " + name +
                " of length " + length +
                " km;";
    }
}

