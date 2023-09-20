package cloud.spring.my.study.algorithm;

import java.util.Scanner;

public class MaxPubSub {

    public static int maxPubSub(String str1, String str2) {
        String longStr = str1.length() > str2.length() ? str1 : str2;
        String shortStr = str1.length() > str2.length() ? str2 : str1;

        int m = shortStr.length() + 1;
        int n = longStr.length() + 1;

        int[][] dp = new int[m][n];

        int maxLen = 0;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (shortStr.charAt(i - 1) == longStr.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {
//            String str1 = in.next();
//            String str2 = in.next();
            String str1 = "addbbccaacbddbbb";
            String str2 = "ddcddcbcdbcbbbdabdcddabddcadbbbbdddabacaadcdcaacd";
            System.out.println(maxPubSub(str1, str2));
//            break;
//        }
    }

}
