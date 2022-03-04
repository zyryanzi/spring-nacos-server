package cloud.spring.my.base.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Solution
 * @Author: uray
 * @Date: 2022-03-02 12:46
 **/
public class Solution {
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 括号的数量是层数的 2 倍
     *
     * @param n
     * @create: 2022/3/3 8:56
     * @return: java.util.List<java.lang.String>
     * @auther: uray
     */
    private static List<String> bracket(int n) {
        List<String> ans = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        backtrackBracket(ans, cur, 0, 0, n);
        return ans;
    }

    private static void backtrackBracket(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max << 1) {
            ans.add(cur.toString());
        }
        if (open < max) {
            cur.append("(");
            backtrackBracket(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrackBracket(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    private static void exam() {
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j >= 0; j--) {
                if (i == j) break;
                for (int k = 0; k >= 0; k++) {
                    if (j == k) break;
                    for (int m = 3; m >= 0; m--) {
                        if (k == m) break;
                        System.out.println(i + " " + j + " " + k + " " + m);
                    }
                }

            }
        }

    }

    public static void main(String[] args) {
//        System.out.println(longestPalindrome("asdfghjklkjhgfdsaasdasdfdsasdfghjg"));
//        System.out.println("1234567890".hashCode() & Integer.MAX_VALUE);
//        System.out.println("254.254.254.254".hashCode());
//        System.out.println("1234dsdskjeiij567890".hashCode());
//        System.out.println(-1L ^ (-1L << 5L));
//        System.out.println(bracket(3));
        exam();
    }
}
