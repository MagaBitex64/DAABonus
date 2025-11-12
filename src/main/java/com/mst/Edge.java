package com.mst;

public class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }

    @Override
    public String toString() {
        return String.format("(%d - %d, weight = %d)", src, dest, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge)) return false;
        Edge other = (Edge) obj;
        return (src == other.src && dest == other.dest) ||
                (src == other.dest && dest == other.src);
    }

    @Override
    public int hashCode() {
        return Math.min(src, dest) * 31 + Math.max(src, dest);
    }
}
