package cloud.spring.my.study.algorithm;

import java.util.Scanner;

public class Maze {

    private static void solveMaze(int[][] maze, int m, int n) {

        maze[0][0] = 0;
        for (int i = 0; i <m; i++) {
            for (int j = 0; j < n; j++) {
                if (maze[i][j+1] == 0) {

                }
            }
        }

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int m = in.nextInt();
            int n = in.nextInt();
            int[][] maze = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    maze[i][j] = in.nextInt();
                }
            }
            solveMaze(maze, m, n);
        }
    }
}
