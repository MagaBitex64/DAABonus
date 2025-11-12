package com.mst;

import java.util.*;

public class Graph {
    private int vertices;
    private List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
        edges.add(new Edge(src, dest, weight));
    }

    public int getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public List<Edge> buildMST() {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges);
        UF uf = new UF(vertices);

        for (Edge edge : edges) {
            if (uf.union(edge.src, edge.dest)) {
                mst.add(edge);
                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }

        return mst;
    }


    public Map<Integer, List<Integer>> findComponents(List<Edge> currentEdges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (Edge edge : currentEdges) {
            adjList.get(edge.src).add(edge.dest);
            adjList.get(edge.dest).add(edge.src);
        }

        boolean[] visited = new boolean[vertices];
        Map<Integer, List<Integer>> components = new HashMap<>();
        int componentId = 0;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adjList, visited, component);
                components.put(componentId++, component);
            }
        }

        return components;
    }

    private void dfs(int vertex, Map<Integer, List<Integer>> adjList,
                     boolean[] visited, List<Integer> component) {
        visited[vertex] = true;
        component.add(vertex);

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adjList, visited, component);
            }
        }
    }

    public Edge findReplacementEdge(List<Edge> mstEdges, Edge removedEdge) {
        Set<Integer> component1 = new HashSet<>();
        Set<Integer> component2 = new HashSet<>();

        Map<Integer, List<Integer>> components = findComponents(mstEdges);

        if (components.size() == 2) {
            component1.addAll(components.get(0));
            component2.addAll(components.get(1));
        }

        Edge replacementEdge = null;
        int minWeight = Integer.MAX_VALUE;

        for (Edge edge : edges) {
            if (edge.equals(removedEdge)) {
                continue;
            }

            boolean srcIn1 = component1.contains(edge.src);
            boolean destIn1 = component1.contains(edge.dest);

            if ((srcIn1 && !destIn1) || (!srcIn1 && destIn1)) {
                if (edge.weight < minWeight) {
                    minWeight = edge.weight;
                    replacementEdge = edge;
                }
            }
        }

        return replacementEdge;
    }
}