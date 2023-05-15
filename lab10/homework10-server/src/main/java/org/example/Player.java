package org.example;

public class Player {
    private String name;
    private int score;
    private int port;

    public Player(String name,int port) {
        this.name = name;
        this.score=0;
        this.port=port;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", port=" + port +
                '}';
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
