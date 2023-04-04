package org.example;

import java.util.List;
import java.util.Random;

public class Robot  implements Runnable {
    private String name;
    private boolean running = true;
    Exploration explore;
    private ExplorationMap.Cell currentCell;
    private int col;
    private int row;
    public Robot(String name) {
        this.name = name;
    }
    public void setExploration(Exploration explore) {
        this.explore = explore;
    }

    public void stop() {
        running = false;
    }

    public void run() {

        while (running) {
            //pick a new cell to explore
            ExplorationMap.Cell newCell = explore.getMap().getUnvisitedCell();
            if (newCell != null) {
                // visit the new cell
                boolean visited = explore.getMap().visit(newCell.getRow2(), newCell.getCol2(), this);
                if (visited) {
                    // extract tokens from shared memory and store them in the cell
                    newCell.storeTokens(explore.getMem().extractTokens(newCell.getSize()));
                }
            } else {
                // no unvisited cells left, terminate the exploration
                stop();
            }
            // make the robot sleep for some time
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }
}
