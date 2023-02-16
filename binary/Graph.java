package com.solvd.fastestalgo.binary;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex from, Vertex to, int weight) {
        // Add edge from 'from' to 'to'
        from.addAdjacentVertex(to, weight);

        // Add edge from 'to' to 'from'
        to.addAdjacentVertex(from, weight);
    }


    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertex(String name) {
        for (Vertex v : vertices) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }


    public List<Edge> getEdges() {
        return edges;
    }
}
