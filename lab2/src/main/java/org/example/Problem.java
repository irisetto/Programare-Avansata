package org.example;

import java.util.*;

/**
 * Problem este clasa ce creeaza ipoteza problemei. Cu ajutorul unei structuri de tip Map se construieste
 * o lista de adiacenta. Aceasta clasa permite adaugarea locatiilor in lista care nu au drumuri conectate,
 * permite crearea legaturilor dintre locatii prin functia addRoute, unde ne vom folosi de clasa Destination,
 * pentru a adauga "nodurile adiacente" ale fiecarei locatii in lista. Functia existPath verifica daca exista
 * legatura intre doua orase conform listei de adiacenta, iar urmatoarele functii in ordine, returneaza rutele existente
 * de la o anumita locatie, returneaza lista de adiacenta care ne va fi de folos in construirea solutiei problemei si ultima
 * returneaza toata lista de adiacenta intr-un mod citibil si placut omului.
 */
public class Problem {

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
    public String checkValidLocation(Location l) {
        if((int)l.getX() == 0 && (int)l.getY() == 0){
            return "Here is the NULL Island, so it's not valid :)";
        }
        return "It is valid!";
    }
    public String checkValidRoad(Road r) {
        if((int)r.getLength() == 0){
            return "You can't have a road of 0 length, hence it's not valid.";
        }
        if(r.getSpeedLimit() == 0){
            return "You can't move at 0km/h, hence it's not valid.";
        }
        return "It is valid!";
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