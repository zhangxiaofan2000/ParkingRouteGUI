package org.example;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphCreationExample {
    public static void main(String[] args) {
        // 创建一个简单图
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);


        // 添加10个节点
        for (int i = 1; i <= 10; i++) {
            graph.addVertex("V-" + i);
        }

        // 添加5个边，边的权重为1
        graph.addEdge("V-1", "V-2");
        graph.addEdge("V-2", "V-3");
        graph.addEdge("V-3", "V-4");
        graph.addEdge("V-4", "V-5");
        graph.addEdge("V-5", "V-1");

        // 输出图的信息
        System.out.println("Graph vertices: " + graph.vertexSet());
        System.out.println("Graph edges: " + graph.edgeSet());

        // 计算最短路径
        DijkstraShortestPath<String, DefaultEdge> shortestPathAlg = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultEdge> shortestPath = shortestPathAlg.getPath("V-1", "V-5");

        // 输出最短路径
        System.out.println("Shortest path from V1 to V5: " + shortestPath.getVertexList() + ", weight=" + shortestPath.getWeight());
    }
}
