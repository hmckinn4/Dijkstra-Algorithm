package com.solvd.fastestalgo.util;

import com.solvd.fastestalgo.binary.Graph;
import com.solvd.fastestalgo.binary.Vertex;

import java.util.*;

public class DijkstraAlgorithm {
    public static Map<Vertex, Integer> shortestPath(Graph graph, Vertex start) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        for (Vertex v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            for (Map.Entry<Vertex, Integer> neighborEntry : current.getAdjacentVertices().entrySet()) {
                Vertex neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();

                if (!visited.contains(neighbor)) {
                    int distance = distances.get(current) + weight;
                    if (distance < distances.get(neighbor)) {
                        distances.put(neighbor, distance);
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances;
    }

    public static List<Vertex> getPath(Vertex end, Map<Vertex, Vertex> previous) {
        List<Vertex> path = new ArrayList<>();
        Vertex current = end;

        while (previous.containsKey(current)) {
            path.add(current);
            current = previous.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    public static Map<Vertex, Vertex> getPreviousMap(Graph graph, Vertex start) {
        Map<Vertex, Vertex> previous = new HashMap<>();
        Map<Vertex, Integer> distances = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        for (Vertex v : graph.getVertices()) {
            distances.put(v, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            for (Map.Entry<Vertex, Integer> neighborEntry : current.getAdjacentVertices().entrySet()) {
                Vertex neighbor = neighborEntry.getKey();
                int weight = neighborEntry.getValue();

                if (!visited.contains(neighbor)) {
                    int distance = distances.get(current) + weight;
                    if (distance < distances.get(neighbor)) {
                        distances.put(neighbor, distance);
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return previous;
    }
}
