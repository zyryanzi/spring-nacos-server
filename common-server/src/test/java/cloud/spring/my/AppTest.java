package cloud.spring.my;

//import static org.junit.Assert.assertTrue;
//
//import com.alibaba.nacos.api.naming.NamingService;
//import org.junit.Test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
//        assertTrue(true);
    }


    public static List<String> brackets(int length) {
        List<String> brackets = new ArrayList<>();
        brackets.add("()");
        if (length == 1) {
            return brackets;
        }
        for (int i = 1; i < length; i++) {
            List<String> newStrs = new ArrayList<>();
            for (String e : brackets) {
                for (int j = 0; j < e.length(); j++) {
                    String newStr = e.substring(0, j) + "()" + e.substring(j);
                    newStrs.add(newStr);
                }
            }
            brackets = newStrs;
        }
        return brackets.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> brackets2(int length) {
        List<String> result = new ArrayList<>();
        genAll(new char[2 * length], 0, result);
        return result;
    }

    private static void genAll(char[] cur, int pos, List res) {
        if (pos == cur.length) {
            if (valid(cur)) {
                res.add(String.valueOf(cur));
            }
        } else {
            cur[pos] = '(';
            genAll(cur, pos + 1, res);
            cur[pos] = ')';
            genAll(cur, pos + 1, res);
        }
    }

    private static boolean valid(char[] cur) {
        int b = 0;
        for (char c : cur) {
            if (c == '(') {
                b++;
            } else {
                b--;
            }
            if (b < 0) {
                return false;
            }
        }
        return b == 0;
    }

    private static int factorial(int length) {
        if (length == 1) {
            return 1;
        }
        return length * (length - 1);
    }

    private static void dfs(int[] nums, int oLength, int depth, List<Integer> cur, List<List<Integer>> ans, boolean[] used) {
        if (depth == oLength) {
            ans.add(new ArrayList<>(cur));
        }
        for (int i = 0; i < oLength; i++) {
            if (!used[i]) {
                cur.add(nums[i]);
                used[i] = true;
                dfs(nums, oLength, depth + 1, cur, ans, used);
                used[i] = false;
                cur.remove(cur.size() - 1);
            }
        }
    }

    /**
     * 全排列 O(n!+n) O(n!)
     *
     * @param nums
     * @create: 2022/3/1 20:51
     * @return: java.util.List<java.util.List       <       java.lang.Integer>>
     * @auther: uray
     */
    private static List<List<Integer>> fPermute(int[] nums) {
        int oLength = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        if (oLength == 0) {
            return ans;
        }
        boolean[] used = new boolean[oLength];
        List<Integer> cur = new ArrayList<>();
        dfs(nums, oLength, 0, cur, ans, used);
        return ans;
    }

    private static void stackBack(List<List<Integer>> ans, List<Integer> cur, int pos, int len) {
        if (pos == len) {
            ans.add(new ArrayList<>(cur));
        }
        for (int i = pos; i < len; i++) {
            Collections.swap(cur, pos, i);
            stackBack(ans, cur, pos + 1, len);
            Collections.swap(cur, pos, i);
        }
    }

    /**
     * 全排列 O(n! + n)
     *
     * @param nums
     * @create: 2022/3/1 20:55
     * @return: java.util.List<java.util.List       <       java.lang.String>>
     * @auther: uray
     */
    private static List<List<Integer>> fPermute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();

        for (Integer i : nums) {
            cur.add(i);
        }

        int pos = 0;
        stackBack(ans, cur, pos, nums.length);
        return ans;
    }

    private static long power(int i, int j) {
        if (j == 1) {
            return i;
        }
        return power(i, j - 1) * i;
    }

    public static void main(String[] args) {
//        System.out.println(1 << 3);
//        System.out.println(brackets2(1 << 3).size());
//        int[] nums = {1, 2, 3, 4, 5};
//        System.out.println(fPermute2(nums));
        System.out.println(power(62, 7));
    }

}
