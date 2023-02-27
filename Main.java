package com.solvd.fastestalgo;

import com.solvd.fastestalgo.binary.User;
import com.solvd.fastestalgo.service.UserService;
import com.solvd.fastestalgo.service.UserServiceImpl;
import com.solvd.fastestalgo.util.DijkstraAlgorithm;
import com.solvd.fastestalgo.binary.Graph;
import com.solvd.fastestalgo.binary.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
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

        // call shortest path algorithm on graph

        Scanner scanner = new Scanner(System.in);
        logger.info("Please enter your name: ");
        String name = scanner.nextLine();

        while (true) {
            logger.info("What town are you starting at? (A, B, C, D, E, F, G): ");
            String startingTownName = scanner.nextLine();
            Vertex startingTown = graph.getVertex(startingTownName);
            if (startingTown == null) {
                logger.error("Invalid town name, please try again.");
                continue;
            }

            logger.info("Which town would you like to go to? (A, B, C, D, E, F, G): ");
            String destinationTownName = scanner.nextLine();
            Vertex destination = graph.getVertex(destinationTownName);
            if (destination == null) {
                logger.error("Invalid town name, please try again.");
                continue;
            }

            Map<Vertex, Integer> distances = DijkstraAlgorithm.shortestPath(graph, startingTown);
            Map<Vertex, Vertex> previous = DijkstraAlgorithm.getPreviousMap(graph, startingTown);
            List<Vertex> path = DijkstraAlgorithm.getPath(destination, previous);
            logger.info("Shortest path from " + startingTown.getName() + " to " + destination.getName() + ": " + path);

            // log user details
            logger.info("Logging user details...");
            String pathString = path.stream().map(Vertex::getName).collect(Collectors.joining(", "));
            UserService userService = new UserServiceImpl();
            User user = userService.logUser(name, startingTownName, destinationTownName, pathString);
            logger.info("User logged successfully: " + user);

            logger.info("Do you want to continue to travel? (1 for Yes, 2 for No): ");
            String answer = scanner.nextLine();
            if (answer.equals("2")) {
                break;
            }
        }
    }
}

