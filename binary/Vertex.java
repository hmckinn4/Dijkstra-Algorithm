package com.solvd.fastestalgo.binary;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
    private String name;
    private Map<Vertex, Integer> adjacentVertices;

    public Vertex(String name) {
        this.name = name;
        adjacentVertices = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<Vertex, Integer> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void addAdjacentVertex(Vertex vertex, int weight) {
        adjacentVertices.put(vertex, weight);
    }

    @Override
    public String toString() {
        return name;
    }
}
