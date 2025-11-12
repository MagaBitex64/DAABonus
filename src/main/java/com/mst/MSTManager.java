package com.mst;

import java.util.*;

public class MSTManager {

    public static void main(String[] args) {
        System.out.println("MST");
        System.out.println();

        Graph graph = createSampleGraph();

        System.out.println("Building MST");
        List<Edge> mst = graph.buildMST();

        System.out.println("Original MST Edges:");
        int totalWeight = 0;
        for (int i = 0; i < mst.size(); i++) {
            Edge edge = mst.get(i);
            System.out.printf("  %d. %s%n", i + 1, edge);
            totalWeight += edge.weight;
        }
        System.out.println("Total MST Weight: " + totalWeight);
        System.out.println("Removing an edge from MST");

        int edgeToRemove = 2;
        Edge removedEdge = mst.get(edgeToRemove);
        System.out.println("Removing edge: " + removedEdge);

        List<Edge> mstAfterRemoval = new ArrayList<>(mst);
        mstAfterRemoval.remove(edgeToRemove);
        System.out.println();

        System.out.println("After removing:");
        Map<Integer, List<Integer>> components = graph.findComponents(mstAfterRemoval);

        System.out.println("Number of components: " + components.size());
        for (Map.Entry<Integer, List<Integer>> entry : components.entrySet()) {
            System.out.println("Component " + (entry.getKey() + 1) + ": " +
                    formatVertices(entry.getValue()));
        }
        System.out.println();

        System.out.println("Replacing");

        Edge replacementEdge = graph.findReplacementEdge(mstAfterRemoval, removedEdge);

        if (replacementEdge != null) {
            System.out.println("Replacement edge found: " + replacementEdge);
            mstAfterRemoval.add(replacementEdge);

            System.out.println();
            System.out.println("New MST after reconnection:");
            int newTotalWeight = 0;
            for (int i = 0; i < mstAfterRemoval.size(); i++) {
                Edge edge = mstAfterRemoval.get(i);
                System.out.printf("  %d. %s", i + 1, edge);
                if (edge.equals(replacementEdge)) {
                    System.out.print(" [NEW EDGE ADDED]");
                }
                System.out.println();
                newTotalWeight += edge.weight;
            }
            System.out.println("New Total MST Weight: " + newTotalWeight);
            System.out.println("Weight difference: +" + (newTotalWeight - totalWeight));
        } else {
            System.out.println("No replacement edge found! Components remain disconnected.");
        }
    }

    private static Graph createSampleGraph() {

        Graph graph = new Graph(7);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 8);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 4);
        graph.addEdge(4, 5, 10);
        graph.addEdge(4, 6, 2);
        graph.addEdge(5, 6, 1);

        System.out.println("Graph created with 7 vertices and 11 edges");
        return graph;
    }

    private static String formatVertices(List<Integer> vertices) {
        Collections.sort(vertices);
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i));
            if (i < vertices.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}