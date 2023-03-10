package org.example;

import java.util.*;


/**
 * Aceasta este clasa principala Main din care vom crea drumuri si locatii, vom putea conecta locatiile prin drumurile create
 * si vom afisa orice informatie este nevoie.
 */
public class Main {
    public static void main(String[] args) {
        CityLocation loc1 = new CityLocation("Bucuresti", 44.4361414, 26.1027202, 2000);
        CityLocation loc2 = new CityLocation("Iasi", 47.174023500000004, 27.57485865552524, 200);
        //daca scoatem argumentul de la loc2Copy vom primi mesaj de instanta la fel
        //CityLocation loc2Copy = new CityLocation("Iasi", 47.174023500000004, 27.57485865552524, 200);
        CityLocation loc3 = new CityLocation("Brasov", 45.0009, 34.8899, 5000);
        CityLocation loc4 = new CityLocation("Sibiu", 45.0009, 34.8899, 5000);

        Road road1 = new Road("E21", Roads.COUNTRY, 2000.0, 30);
        Road road2 = new Road("D24", Roads.EXPRESS, 1000.0, 30); //daca punem 3 la length primim mesajul de euclidian
        Problem route = new Problem();
        route.addRoute(loc1, loc2, road1);
        route.addRoute(loc4, loc3, road2);
        route.addLocation(loc4);
        System.out.println(loc1);
        System.out.println(road1);
        System.out.println(road1.getLength());
        road1.setType(Roads.EXPRESS);
        System.out.println(road1.getType());
        System.out.println(route.getRoutesOf(loc4));
        System.out.println(route.existPath(loc4, loc3));
        AirportLocation l = new AirportLocation("Something", 0.0, 0.0, 3);
        System.out.println(route.checkValidLocation(l));
        Road r = new Road("Blank", Roads.COUNTRY, 0.0, 30);
        System.out.println(route.checkValidRoad(r));
    }
}