package cloud.spring.my.study.algorithm;

import java.util.*;

/**
 * 使用bfs广度优先遍历，具体实现方式是将二维数组转化为一维数组，然后对满足条件的进行遍历
 */
public class RottenOrange {

    // dr,dc 配合使用得到 grid[r][c] 上grid[r-1][c]，左grid[r][c-1]，下grid[r+1][c]，右grid[r][c+1]的元素
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, -1, 0, 1};

    public static int orangesRotting(int[][] grid) {
        // 二维数组行数、列数
        int R = grid.length, C = grid[0].length;
        // 队列 用于存放最开始腐烂的橘子
        Queue<Integer> queue = new ArrayDeque<>();
        // 保存腐烂的橘子的腐烂时间，key是二维数组转化为一维数组的下标，value为腐烂时间
        Map<Integer, Integer> depth = new HashMap<>();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == 2) {
                    // 转化为一维数组，行值 * 每行列数 + 列值，便于存入队列
                    int code = r * C + c;
                    queue.add(code);
                    depth.put(code, 0);
                }
            }
        }
        int ans = 0;
        // 广度优先遍历在这里体现，对queue中的元素遍历并在循环体中更新
        while (!queue.isEmpty()) {
            int code = queue.remove();
            // 从队列取出并算出二维位置
            int r = code / C, c = code % C;
            // 上下左右四个方位
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }
        // 判断是否仍然有新鲜橘子
        for (int[] row : grid) {
            for (int v : row) {
                if (v == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1, 0},
                {1, 1, 0, 1},
                {0, 1, 1, 2},
                {0, 1, 1, 1}
        };
        System.out.println(orangesRotting(grid));
    }
}

