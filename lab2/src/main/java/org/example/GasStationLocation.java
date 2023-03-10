package org.example;

public class GasStationLocation extends Location {
    private float gasPrice;

    public GasStationLocation(String name, Double x, Double y, float gasPrice) {
        super(name, "GAS STATION", x, y);
        this.gasPrice = gasPrice;
    }
}
