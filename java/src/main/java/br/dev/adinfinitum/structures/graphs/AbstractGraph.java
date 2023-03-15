package br.dev.adinfinitum.structures.graphs;

import java.util.regex.Pattern;

public abstract class AbstractGraph {

    protected static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*\\.?[0-9]+");

    protected static final Pattern LABEL_PATTERN = Pattern.compile("\"([^\"]*)\"");

    protected static final String NON_CONFORMANT_FILE = "Arquivo não conforme com o formato de entrada";

    protected String[] labels;
    protected int edges = 0;

    public abstract int getVerticesQnt();

    public int getEdgesQnt() {
        return edges;
    }

    public abstract int degree(int v);

    public String label(int v) {
        return labels[v];
    }

    public abstract int[] neighbours(int v);

    public abstract boolean hasEdge(int v1, int v2);

    public abstract void read(String file);

    public abstract void prettyPrint();
}
