package org.example;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphUtils {
    public static GraphPath<String, DefaultEdge> DijkstraPath(char[][] array,String start,String end){
        // 创建一个简单图
        Graph<String, DefaultEdge> graph = createGraphFromCharArray(array);
        // 计算最短路径
        DijkstraShortestPath<String, DefaultEdge> shortestPathAlg = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultEdge> shortestPath = shortestPathAlg.getPath(start, end);

        // 输出最短路径
        System.out.println("最短路径: " + shortestPath.getVertexList() + ", weight=" + shortestPath.getWeight());

        return shortestPath;


    }
    public static GraphPath<String, DefaultEdge> AStarPath(char[][] array,String start,String end){
        // 创建一个简单图
        Graph<String, DefaultEdge> graph = createGraphFromCharArray(array);

        // 计算最短路径
        AStarAdmissibleHeuristic<String> heuristic = new ManhattanDistance();
        AStarShortestPath<String, DefaultEdge> shortestPathAlg = new AStarShortestPath<>(graph, heuristic);
        GraphPath<String, DefaultEdge> shortestPath = shortestPathAlg.getPath(start, end);

        // 输出最短路径
        System.out.println("最短路径: " + shortestPath.getVertexList() + ", weight=" + shortestPath.getWeight());

        return shortestPath;


    }

    public static DefaultDirectedGraph<String, DefaultEdge> createGraphFromCharArray(char[][] array) {
        DefaultDirectedGraph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        // 添加所有节点
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                String nodeName = i + "-" + j;
                graph.addVertex(nodeName);
            }
        }
        // 添加所有边
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                String nodeName = i + "-" + j;
                if (array[i][j] == 'R' | array[i][j] == 'S') {
                    if (i > 0) {
                        graph.addEdge(nodeName, (i - 1) + "-" + j);
                    }
                    if (j > 0) {
                        graph.addEdge(nodeName, i + "-" + (j - 1));
                    }
                    if (i < array.length - 1) {
                        graph.addEdge(nodeName, (i + 1) + "-" + j);
                    }
                    if (j < array[0].length - 1) {
                        graph.addEdge(nodeName, i + "-" + (j + 1));
                    }
                }
                if (array[i][j] == 'E' ) {
                    if (i > 0 & array[i - 1][j] == 'R') {
                        graph.addEdge(nodeName, (i - 1) + "-" + j);
                    }
                    if (j > 0 & array[i][j - 1] == 'R') {
                        graph.addEdge(nodeName, i + "-" + (j - 1));
                    }
                    if (i < array.length - 1 & array[i + 1][j] == 'R') {
                        graph.addEdge(nodeName, (i + 1) + "-" + j);
                    }
                    if (j < array[0].length - 1 & array[i][j + 1] == 'R') {
                        graph.addEdge(nodeName, i + "-" + (j + 1));
                    }
                }

            }
        }
        return graph;
    }



    }
