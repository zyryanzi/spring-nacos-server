package cloud.spring.my.study.algorithm;

import java.util.Scanner;

/**
 * 使用一个实例来演示递归和动态规划的结合，以体会他们的思想<br />
 * <br />
 * 机器人移动问题: <br />
 * 对于N个格子（从1~N标号），机器人最开始在Start（1<=Start<=N）位置，<br />
 * 要求在走过K（K>=1）步后（一次一格），来到aim位置（1<=aim<=N），<br />
 * 问机器人有多少种走法？
 */
public class RecurAndDp {

    /**
     * 计算路线种类方法
     *
     * @param n     格子数
     * @param start 起始点位
     * @param k     走过的步数
     * @param aim   目标点位
     * @return 走法数量
     */
    public static int ways(int n, int start, int k, int aim) {
        if (n < 2 || start < 1 || start > n || k < 1 || aim < 1 || aim > n) {
            return -1;
        }

        // 初始化 dp 表
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                dp[i][j] = -1;
            }
        }
        
        return process(n, start, k, aim, dp);
    }

    private static int process(int n, int current, int remain, int aim, int[][] dp) {
        if (dp[current][remain] != -1) {
            return dp[current][remain];
        }

        int ans;
        if (remain == 0) {
            //  到达位置
            ans = current == aim ? 1 : 0;
        } else if (current == 1) {
            // 只能往右
            ans = process(n, 2, remain - 1, aim, dp);
        } else if (current == n) {
            // 只能往左
            ans = process(n, n - 1, remain - 1, aim, dp);
        } else {
            // 两边都可以
            ans = process(n, current - 1, remain - 1, aim, dp)
                    + process(n, current + 1, remain - 1, aim, dp);
        }

        dp[current][remain] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(ways(4, 1, 3, 2));
        if (in.hasNext()) {
            System.out.println(in.next());
        }
    }

}
