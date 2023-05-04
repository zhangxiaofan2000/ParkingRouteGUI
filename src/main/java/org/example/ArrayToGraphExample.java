package org.example;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class ArrayToGraphExample {

    public static void main(String[] args) {
        // 定义二维数组
        char[][] array = {
                {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
                {'R', 'C', 'C', 'C', 'E', 'R', 'C', 'C', 'C', 'R'},
                {'R', 'C', 'C', 'C', 'C', 'R', 'C', 'E', 'C', 'R'},
                {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
                {'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R'},
                {'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R'},
                {'S', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'}
        };

        // 创建一个简单图
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

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
                if (array[i][j] == 'R' | array[i][j] == 'S' ) {
                    if (i > 0 ) {
                        graph.addEdge(nodeName, (i - 1) + "-" + j);
                    }
                    if (j > 0) {
                        graph.addEdge(nodeName, i + "-" + (j - 1));
                    }
                    if (i < array.length - 1 ) {
                        graph.addEdge(nodeName, (i + 1) + "-" + j);
                    }
                    if (j < array[0].length - 1) {
                        graph.addEdge(nodeName, i + "-" + (j + 1));
                    }
                }
                if (array[i][j] == 'E'| array[i][j] == 'C' ) {
                    if (i > 0 & ( array[ i- 1][j] != 'C' | array[ i- 1][j] != 'E')  ) {
                        graph.addEdge(nodeName, (i - 1) + "-" + j);
                    }
                    if (j > 0  & ( array[ i][j - 1] != 'C' | array[ i][j - 1] != 'E')) {
                        graph.addEdge(nodeName, i + "-" + (j - 1));
                    }
                    if (i < array.length - 1  & ( array[i + 1][j] != 'C' | array[ i + 1][j] != 'E')) {
                        graph.addEdge(nodeName, (i + 1) + "-" + j);
                    }
                    if (j < array[0].length - 1  & ( array[i][j + 1] != 'C' | array[ i][j + 1] != 'E') ) {
                        graph.addEdge(nodeName, i + "-" + (j + 1));
                    }
                }

            }
        }    // 打印图
        System.out.println(graph.toString());
        // 计算最短路径
        DijkstraShortestPath<String, DefaultEdge> shortestPathAlg = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultEdge> shortestPath = shortestPathAlg.getPath("0-0", "4-4");

        // 输出最短路径
        System.out.println("Shortest path from V1 to V5: " + shortestPath.getVertexList() + ", weight=" + shortestPath.getWeight());


    }
}

