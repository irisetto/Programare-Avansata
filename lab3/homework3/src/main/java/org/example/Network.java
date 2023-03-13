package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Network {
    private List<Node> nodes;

    public Network() {
        this.nodes = new ArrayList<>();
    }

    public static int compareByConnections(Node o1, Node o2) {
        if (o1.getRelationships().size() == o2.getRelationships().size())
            return 0;
        else if (o1.getRelationships().size() < o2.getRelationships().size())
            return 1;
        else return -1;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public String importanceOf(Node node) {
        Collections.sort(nodes, Network::compareByConnections);
        return "Importance of " + node.getName() + " is " + (nodes.indexOf(node) + 1)
                + " with " + node.getRelationships().size() + (node.getRelationships().size() > 1 ? " connections." : " connection.");
    }

    public void print() {
        Collections.sort(nodes, Network::compareByConnections);
        System.out.println(nodes);
    }
}
