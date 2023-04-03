package org.example;

import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private final SharedMemory mem ;
    private final ExplorationMap map ;
    private final List<Robot> robots = new ArrayList<>();
    public Exploration(int n) {
        mem = new SharedMemory(n);
        map = new ExplorationMap(n);
    }
    public void addRobot(Robot robot){
        robot.setExploration(this);
        robots.add(robot);
    }
    public void start() {
        for (Robot robot : robots) {
            new Thread(robot).start();
        }
    }
    public SharedMemory getMem() {
        return mem;
    }

    public ExplorationMap getMap() {
        return map;
    }

    public static void main(String args[]) {
        var explore = new Exploration(3);
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
        explore.start();
    }

}
