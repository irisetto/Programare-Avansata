package org.example;

import java.util.*;

/**
 * CityLocation, AirportLocation si GasStationLocation sunt clase ce extind clasa de baza abstracta Location.
 * Acestea au un constructor ce apeleaza constructorul superclasei si, de asemenea, fiecare au si o proprietate
 * specifica tipului de locatie.
 */
class CityLocation extends Location {
    private long population;

    public CityLocation(String name, Double x, Double y, long population) {
        super(name, "CITY", x, y);
        this.population = population;
    }
}

class AirportLocation extends Location {
    private int terminals;

    public AirportLocation(String name, Double x, Double y, int terminals) {
        super(name, "AIRPORT", x, y);
        this.terminals = terminals;
    }
}

class GasStationLocation extends Location {
    private float gasPrice;

    public GasStationLocation(String name, Double x, Double y, float gasPrice) {
        super(name, "GAS STATION", x, y);
        this.gasPrice = gasPrice;
    }
}

enum Roads {
    HIGHWAY,
    EXPRESS,
    COUNTRY
}

/**
 * Location este o clasa abstracta ce este si clasa de baza pentru crearea locatiilor. In constructor
 * initializam variabilele si cu ajutorul HashSet-ului pentru fiecare combinatie de valori se creeaza o cheie hash,
 * si in caz ca locatia exista deja se afiseaza un mesaj. In aceasta clasa avem getters si setters corespunzatori
 * si doua functii suprascrise, toString care returneaza modul in care se va interpreta obiectul si equals care
 * verifica daca doua obiecte de tip locatie sunt egale.
 */
abstract class Location {
    private String name;
    private String type;
    private Double x;
    private Double y;
    private static Set<Integer> existingHashes = new HashSet<>();

    public Location(String name, String type, Double x, Double y) {

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

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
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
        if (!getX().equals(location.getX())) return false;
        return getY().equals(location.getY());
    }

}

/**
 * Road este o clasa folosita pentru crearea drumurilor, care asemanator clasei Location interzice crearea obiectelor ce exista deja.
 * Ce o diferentiaza de clasa Location este ca in aceasta clasa tipul drumului se va defini printr-un enum, nu prin clase mostenite cum am observat mai sus.
 */
class Road {
    private String name;
    private Roads type;
    private Double length;
    private Integer speedLimit;
    private static Set<Integer> existingHashes = new HashSet<>();

    public Road(String name, Roads type, Double length, Integer speedLimit) {

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

    public Double getLength() {
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

    public void setLength(Double length) {
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
        if (!getLength().equals(road.getLength())) return false;
        return getSpeedLimit().equals(road.getSpeedLimit());
    }

}

/**
 * Clasa Destination este o clasa ajutatoare in crearea listei de adiacenta a rutelor dintre locatii.
 * Pentru fiecare locatie exista o lista de tipul Destination in care se mentioneaza in ce locatii se pot
 * ajunge, pe ce drum si cu ce lungime.
 */
class Destination {
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

/**
 * Problem este clasa ce creeaza ipoteza problemei. Cu ajutorul unei structuri de tip Map se construieste
 * o lista de adiacenta. Aceasta clasa permite adaugarea locatiilor in lista care nu au drumuri conectate,
 * permite crearea legaturilor dintre locatii prin functia addRoute, unde ne vom folosi de clasa Destination,
 * pentru a adauga "nodurile adiacente" ale fiecarei locatii in lista. Functia existPath verifica daca exista
 * legatura intre doua orase conform listei de adiacenta, iar urmatoarele functii in ordine, returneaza rutele existente
 * de la o anumita locatie, returneaza lista de adiacenta care ne va fi de folos in construirea solutiei problemei si ultima
 * returneaza toata lista de adiacenta intr-un mod citibil si placut omului.
 */
class Problem {

    private Map<Location, List<Destination>> routeList;

    public Problem() {
        routeList = new HashMap<>();
    }

    public void addLocation(Location location) {
        routeList.putIfAbsent(location, new ArrayList<>());
    }

    public void addRoute(Location l1, Location l2, Road r) {
        //vom face aici verificarea de distanta euclidiana
        Double distance = Math.sqrt(Math.pow(l2.getX() - l1.getX(), 2) + Math.pow(l2.getY() - l1.getY(), 2));
        if (r.getLength() < distance) {
            System.out.println("The length of the road is less than the euclidian distance between the locations coordinates");
            System.exit(0);
        }
        //pt un drum ce conecteaza 2 locatii, adaugam in map cele 2 locatii si pt fiecare dintre ele capatul opus ca destinatie
        routeList.putIfAbsent(l1, new ArrayList<>());
        routeList.putIfAbsent(l2, new ArrayList<>());
        Destination dest1 = new Destination(l1, r.getLength(), r.getName());
        routeList.get(l2).add(dest1);
        Destination dest2 = new Destination(l2, r.getLength(), r.getName());
        routeList.get(l1).add(dest2);

    }

    public boolean existPath(Location start, Location end) {
        Queue<Location> queue = new LinkedList<>();
        Set<Location> visited = new HashSet<>();
        //adaugam locatia start in coada si o setam ca vizitat
        queue.offer(start);
        visited.add(start);
        //facem un bfs
        while (!queue.isEmpty()) {
            Location current = queue.poll();
            if (current == end)
                return true;
            //n am ajuns la acea locatie si iteram prin vecini
            for (Destination destination : routeList.get(current)) {
                Location adjacentLocation = destination.getLocation();
                if (!visited.contains(adjacentLocation)) {
                    queue.offer(adjacentLocation);
                    visited.add(adjacentLocation);
                }
            }

        }
        return false;
    }

    public String getRoutesOf(Location location) {
        List<Destination> destinations = routeList.get(location);
        StringBuilder sb = new StringBuilder();
        sb.append("Start Location: ").append(location.getName()).append("\n");
        for (Destination destination : destinations)
            sb.append(destination).append("\n");
        return sb.toString();
    }

    public Map<Location, List<Destination>> getRoutesList() {
        return routeList;
    }

    public String allRoutes() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Location, List<Destination>> entry : routeList.entrySet()) {
            Location location = entry.getKey();
            List<Destination> destinations = entry.getValue();
            // adaug locatie
            sb.append("Start Location: ").append(location.getName()).append("\n");

            //adaug rute
            for (Destination destination : destinations) {
                sb.append(destination).append("\n");
            }
        }
        return sb.toString();
    }
}

/**
 * Aceasta este clasa principala Main din care vom crea drumuri si locatii, vom putea conecta locatiile prin drumurile create
 * si vom afisa orice informatie este nevoie.
 */
public class Main {
    public static void main(String[] args) {
        Location loc1 = new CityLocation("Bucuresti", 44.4361414, 26.1027202, 2000);
        Location loc2 = new CityLocation("Iasi", 47.174023500000004, 27.57485865552524, 200);
        //daca scoatem argumentul de la loc2Copy vom primi mesaj de instanta la fel
        //Location loc2Copy = new CityLocation("Iasi", 47.174023500000004, 27.57485865552524, 200);
        Location loc3 = new CityLocation("Brasov", 45.0009, 34.8899, 5000);
        Location loc4 = new CityLocation("Sibiu", 45.0009, 34.8899, 5000);

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

    }
}
