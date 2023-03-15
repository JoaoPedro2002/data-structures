package br.dev.adinfinitum.structures.graphs;

public class WeightedMatrixGraph extends MatrixGraph implements Weighted {
    private static final Float NULL_VALUE = Float.MAX_VALUE;

    @Override
    public int degree(int v) {
        return degree(v, NULL_VALUE);
    }

    @Override
    public int[] neighbours(int v) {
        return neighbours(v, NULL_VALUE);
    }

    @Override
    public boolean hasEdge(int v1, int v2) {
        return !getEdged(v1, v2).equals(NULL_VALUE);
    }

    @Override
    public Float weight(int v1, int v2) {
        return (Float) matrix[v1][v2];
    }

    @Override
    public void read(String file) {
        read(file, NULL_VALUE);
    }
}
