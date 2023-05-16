package org.example;

public class Player {
    private String name;
    private int port;

    public Player(String name, int port) {
        this.name = name;
        this.port = port;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", port=" + port +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
