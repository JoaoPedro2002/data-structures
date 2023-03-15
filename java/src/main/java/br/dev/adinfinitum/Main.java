package br.dev.adinfinitum;

import br.dev.adinfinitum.structures.graphs.MatrixGraph;
import br.dev.adinfinitum.structures.graphs.WeightedMatrixGraph;
import br.dev.adinfinitum.utils.FileUtils;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        WeightedMatrixGraph weightedGraph = new WeightedMatrixGraph();
        weightedGraph.read(FileUtils.getCompletePathFromResources("instancias/arvore_geradora_minima/agm_tiny.net"));
        weightedGraph.prettyPrint();

        MatrixGraph graph = new MatrixGraph();
        graph.read(FileUtils.getCompletePathFromResources("instancias/arvore_geradora_minima/agm_tiny.net"));
        graph.prettyPrint();
    }
}