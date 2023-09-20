package cloud.spring.my.study.algorithm;

import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * 从 RecurAndDp 升级而来
 */
public class StepsAndWays {

    public static int StepsAndWays(int n, int start, int k, int aim) {
        if (n < 2 || start < 1 || start > n || aim < 1 || aim > n || k < 1) {
            return -1;
        }

        // 当前位置 1 - n, 剩余步数 0 - k, n * (k + 1) 即可涵盖所有情况
        int[][] dp = new int[n + 1][k + 1];
        // 到达目标位置，剩余步数刚好为0，则为1种方法
        dp[aim][0] = 1;
        // 由终点出发，计算出所有点的走法数，依赖左侧（步数少一），左上或左下（不可能步数减少一，位置还不变）
        // 由最左列剩余步数开始算，必须这样，以终点为起点
        for (int rem = 1; rem < k + 1; rem++) {
            for (int cur = 1; cur <= n; cur++) {
                if (cur == n) {
                    // 边界
                    dp[cur][rem] = dp[cur - 1][rem - 1];
                } else {
                    dp[cur][rem] = dp[cur - 1][rem - 1] + dp[cur + 1][rem - 1];
                }
            }
        }
        return dp[start][k];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = StepsAndWays(5, 2, 6, 4);
        System.out.println(i);
        in.hasNext();
    }

}
