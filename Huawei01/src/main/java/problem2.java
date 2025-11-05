import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.io.*;

public class problem2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 读取地图大小
        int N = Integer.parseInt(br.readLine().trim());

        // 读取地图糖果信息
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(row[j]);
            }
        }

        // 读取妈妈和宝宝的位置
        String[] positions = br.readLine().split(" ");
        int startX = Integer.parseInt(positions[0]);
        int startY = Integer.parseInt(positions[1]);
        int endX = Integer.parseInt(positions[2]);
        int endY = Integer.parseInt(positions[3]);

        // 计算结果
        int result = findMaxCandyInShortestPath(grid, startX, startY, endX, endY, N);
        System.out.println(result);
    }

    private static int findMaxCandyInShortestPath(int[][] grid, int startX, int startY,
                                                  int endX, int endY, int N) {
        // 如果起点就是终点
        if (startX == endX && startY == endY) {
            return grid[startX][startY];
        }

        // 方向数组：上、右、下、左
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // 记录到达每个位置的最小步数
        int[][] minSteps = new int[N][N];
        // 记录在最小步数下能获得的最大糖果数
        int[][] maxCandy = new int[N][N];

        // 初始化数组
        for (int i = 0; i < N; i++) {
            Arrays.fill(minSteps[i], Integer.MAX_VALUE);
            Arrays.fill(maxCandy[i], -1);
        }

        // BFS队列
        Queue<int[]> queue = new LinkedList<>();

        // 初始化起点
        minSteps[startX][startY] = 0;
        maxCandy[startX][startY] = grid[startX][startY];
        queue.offer(new int[]{startX, startY});

        int minTimeToChild = Integer.MAX_VALUE;
        int maxCandyResult = 0;
        int currentSteps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // 如果当前步数已经大于已知的最短时间，可以提前结束
            if (currentSteps > minTimeToChild) {
                break;
            }

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                int currentCandy = maxCandy[x][y];

                // 如果到达宝宝位置
                if (x == endX && y == endY) {
                    if (currentSteps < minTimeToChild) {
                        minTimeToChild = currentSteps;
                        maxCandyResult = currentCandy;
                    } else if (currentSteps == minTimeToChild) {
                        maxCandyResult = Math.max(maxCandyResult, currentCandy);
                    }
                    continue;
                }

                // 遍历四个方向
                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    // 检查新位置是否有效
                    if (newX >= 0 && newX < N && newY >= 0 && newY < N && grid[newX][newY] != -1) {
                        int newCandy = currentCandy + grid[newX][newY];
                        int newSteps = currentSteps + 1;

                        // 如果找到更短的路径，或者相同路径但更多糖果
                        if (newSteps < minSteps[newX][newY] ||
                                (newSteps == minSteps[newX][newY] && newCandy > maxCandy[newX][newY])) {

                            minSteps[newX][newY] = newSteps;
                            maxCandy[newX][newY] = newCandy;
                            queue.offer(new int[]{newX, newY});
                        }
                    }
                }
            }
            currentSteps++;
        }

        return maxCandyResult;
    }

}
