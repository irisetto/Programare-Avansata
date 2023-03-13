package org.example;

import java.util.Map;
import java.util.Objects;

public interface Node {
    String getName();

    Map<String, Node> getRelationships();
}
