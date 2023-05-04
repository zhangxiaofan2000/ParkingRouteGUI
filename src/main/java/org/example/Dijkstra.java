package org.example;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
    private MapGraph graph;

    public Dijkstra(MapGraph graph) {
        this.graph = graph;
    }

    // 迪杰斯特拉算法求解最短路径
    public int shortestPath() {
        int start = findStart();
        int end = findEnd();
        int[] dist = new int[graph.ROWS * graph.COLS];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        pq.offer(start);

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            for (int i = 0; i < graph.ROWS * graph.COLS; i++) {
                if (graph.graph[cur][i] != 0) {
                    int newDist = dist[cur] + graph.graph[cur][i];
                    if (newDist < dist[i]) {
                        dist[i] = newDist;
                        pq.offer(i);
                    }
                }
            }
        }

        return dist[end];
    }

    // 找到起点在数组中的位置
    private int findStart() {
        for (int i = 0; i < graph.ROWS * graph.COLS; i++) {
            if (graph.map[i / graph.COLS][i % graph.COLS] == 'S') {
                return i;
            }
        }
        return -1;
    }

    // 找到终点在数组中的位置
    private int findEnd() {
        for (int i = 0; i < graph.ROWS * graph.COLS; i++) {
            if (graph.map[i / graph.COLS][i % graph.COLS] == 'E') {
                return i;
            }
        }
        return -1;
    }
}
