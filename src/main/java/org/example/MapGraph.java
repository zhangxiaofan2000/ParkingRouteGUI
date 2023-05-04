package org.example;

public class MapGraph {
    private final int ROWS = 7;
    private final int COLS = 10;
    private char[][] map = {
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', 'C', 'C', 'C', 'E', 'R', 'C', 'C', 'C', 'R'},
            {'R', 'C', 'C', 'C', 'C', 'R', 'C', 'E', 'C', 'R'},
            {'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'},
            {'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R'},
            {'R', 'C', 'C', 'C', 'C', 'R', 'C', 'C', 'C', 'R'},
            {'S', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R', 'R'}
    };
    private int[][] graph = new int[ROWS * COLS][ROWS * COLS];

    public MapGraph() {
        buildGraph();
    }

    // 将地图转化为有向图
    private void buildGraph() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int from = i * COLS + j;
                int to;
                // 右边
                if (j < COLS - 1 && map[i][j + 1] == 'R') {
                    to = i * COLS + j + 1;
                    graph[from][to] = 1;
                }
                // 下边
                if (i < ROWS - 1 && map[i + 1][j] == 'R') {
                    to = (i + 1) * COLS + j;
                    graph[from][to] = 1;
                }
                // 左边
                if (j > 0 && map[i][j - 1] == 'R') {
                    to = i * COLS + j - 1;
                    graph[from][to] = 1;
                }
                // 上边
                if (i > 0 && map[i - 1][j] == 'R') {
                    to = (i - 1) * COLS + j;
                    graph[from][to] = 1;
                }
            }
        }
    }

    public int[][] getGraph() {
        return graph;
    }


}
