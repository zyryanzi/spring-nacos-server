package cloud.spring.my.study.algorithm;

import java.util.Scanner;

public class WeighingWeights {

    public static int weighingWeights(int[] weights, int[] num) {

        int weightSum = 0;
        for (int i = 0; i < weights.length; i++) {
            weightSum += weights[i] * num[i];
        }

        boolean[] dp = new boolean[weightSum + 1];
        dp[0] = true;

        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < num[i]; j++) {
                for (int k = weightSum; k >= weights[i]; k--) {
                    if (dp[k - weights[i]]) {
                        dp[k] = true;
                    }
                }
            }
        }

        int count = 0;
        for (boolean dpB : dp) {
            if (dpB) {
                count++;
            }
        }

        return count;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            int[] nums = new int[n];
            int[] weights = new int[n];
            for(int i = 0; i < n; i++) {
                int nextInt = in.nextInt();
                weights[i] = nextInt;
            }
            for(int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }

            System.out.println(weighingWeights(weights, nums));
            break;
        }
    }
}
