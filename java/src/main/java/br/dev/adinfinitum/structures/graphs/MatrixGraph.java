package br.dev.adinfinitum.structures.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;

public class MatrixGraph extends AbstractGraph {

    private static final Byte NULL_VALUE = 0;
    protected Number[][] matrix;

    @Override
    public int getVerticesQnt() {
        return matrix.length;
    }

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
    public void read(String file) {
        read(file, NULL_VALUE);
    }

    protected void read(String file, Number nullValue) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            readVertices(reader);
            readEdges(reader);
            normalizeMatrix(nullValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void readEdges(BufferedReader reader) throws IOException {
        Matcher matcher;
        String line = reader.readLine();
        if (!line.contains("*edges")) throw new IllegalStateException(NON_CONFORMANT_FILE);
        int vertice1, vertice2;
        line = reader.readLine();
        while (line != null) {
            matcher = NUMBER_PATTERN.matcher(line);
            matcher.find();
            vertice1 = Integer.parseInt(matcher.group()) - 1;
            matcher.find();
            vertice2 = Integer.parseInt(matcher.group()) - 1;
            if (this instanceof Weighted) {
                matcher.find();
                float weight = Float.parseFloat(matcher.group());
                matrix[vertice1][vertice2] = weight;
            } else {
                matrix[vertice1][vertice2] = 1;
            }
            line = reader.readLine();
            edges++;
        }
    }

    protected void readVertices(BufferedReader reader) throws IOException {
        String line;
        Matcher matcher;
        line = reader.readLine();
        matcher = NUMBER_PATTERN.matcher(line);
        if (!matcher.find()) throw new IllegalStateException(NON_CONFORMANT_FILE);
        int vertices = Integer.parseInt(matcher.group());
        matrix = new Number[vertices][vertices];
        labels = new String[vertices];
        for (int i = 0; i < vertices; i++) {
            line = reader.readLine();
            matcher = LABEL_PATTERN.matcher(line);
            if (!matcher.find()) throw new IllegalStateException(NON_CONFORMANT_FILE);
            labels[i] = matcher.group().replace("\"", "");
        }
    }

    protected void normalizeMatrix(Number nullValue) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Objects.requireNonNullElse(matrix[i][j], nullValue);
            }
        }
    }

    @Override
    public void prettyPrint() {
        System.out.printf("%20s", "");
        for (int i = 0; i < labels.length; i++) {
            System.out.printf("%20s", labels[i]);
        }
        System.out.println();
        for(int i=0; i< matrix.length; i++){
            System.out.printf("%20s", labels[i]);
            for(int j=0; j< matrix.length; j++){
                System.out.printf("%20s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    protected Number getEdged(int v1, int v2) {
        return matrix[v1][v2];
    }

    protected int[] neighbours(int v, Number nullValue) {
        int[] neighbours = new int[matrix.length];
        int len = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (!matrix[v][i].equals(nullValue)) {
                neighbours[len] = i;
                len++;
            }
        }
        return Arrays.copyOf(neighbours, len);
    }

    protected int degree(int v, Number nullValue) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (!matrix[v][i].equals(nullValue)) count++;
        }
        return count;
    }
}
