package com.solvd.fastestalgo;

import com.solvd.fastestalgo.binary.DijkstraAlgorithm;
import com.solvd.fastestalgo.binary.Graph;
import com.solvd.fastestalgo.binary.Vertex;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        // Add vertices to graph
        Vertex townA = new Vertex("A");
        Vertex townB = new Vertex("B");
        Vertex townC = new Vertex("C");
        Vertex townD = new Vertex("D");
        Vertex townE = new Vertex("E");
        Vertex townF = new Vertex("F");
        Vertex townG = new Vertex("G");
        graph.addVertex(townA);
        graph.addVertex(townB);
        graph.addVertex(townC);
        graph.addVertex(townD);
        graph.addVertex(townE);
        graph.addVertex(townF);
        graph.addVertex(townG);

        // Add edges to graph
        graph.addEdge(townA, townB, 5);
        graph.addEdge(townA, townC, 3);
        graph.addEdge(townB, townC, 2);
        graph.addEdge(townB, townD, 6);
        graph.addEdge(townC, townD, 4);
        graph.addEdge(townC, townE, 8);
        graph.addEdge(townD, townE, 7);
        graph.addEdge(townD, townF, 10);
        graph.addEdge(townE, townF, 9);
        graph.addEdge(townF, townG, 11);

        // Call shortest path algorithm on graph

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("What town are you starting at? (A, B, C, D, E, F, G): ");
            String startingTownName = scanner.nextLine();
            Vertex startingTown = graph.getVertex(startingTownName);
            if (startingTown == null) {
                System.out.println("Invalid town name, please try again.");
                continue;
            }

            System.out.print("Which town would you like to go to? (A, B, C, D, E, F, G): ");
            String destinationTownName = scanner.nextLine();
            Vertex destination = graph.getVertex(destinationTownName);
            if (destination == null) {
                System.out.println("Invalid town name, please try again.");
                continue;
            }

            Map<Vertex, Integer> distances = DijkstraAlgorithm.shortestPath(graph, startingTown);
            Map<Vertex, Vertex> previous = DijkstraAlgorithm.getPreviousMap(graph, startingTown);
            List<Vertex> path = DijkstraAlgorithm.getPath(destination, previous);
            System.out.println("Shortest path from " + startingTown.getName() + " to " + destination.getName() + ": " + path);

            System.out.print("Do you want to continue to travel? (1 for Yes, 2 for No): ");
            String answer = scanner.nextLine();
            if (answer.equals("2")) {
                break;
            }
        }

        scanner.close();
    }
}
