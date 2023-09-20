package cloud.spring.my.study.algorithm;

public class SimpleReg {


    /**
     *
     * 题目中的匹配是一个「逐步匹配」的过程：我们每次从字符串 ppp 中取出一个字符或者「字符 + 星号」的组合，并在 sss 中进行匹配。对于 ppp 中一个字符而言，它只能在 sss 中匹配一个字符，匹配的方法具有唯一性；而对于 ppp 中字符 + 星号的组合而言，它可以在 sss 中匹配任意自然数个字符，并不具有唯一性。因此我们可以考虑使用动态规划，对匹配的方案进行枚举。
     *
     * 我们用 f[i][j]f[i][j]f[i][j] 表示 sss 的前 iii 个字符与 ppp 中的前 jjj 个字符是否能够匹配。在进行状态转移时，我们考虑 ppp 的第 jjj 个字符的匹配情况：
     *
     * 如果 ppp 的第 jjj 个字符是一个小写字母，那么我们必须在 sss 中匹配一个相同的小写字母，即
     *
     * f[i][j]={f[i−1][j−1],s[i]=p[j]false,s[i]≠p[j]f[i][j] = \begin{cases} f[i - 1][j - 1], & s[i] = p[j]\\ \text{false}, & s[i] \neq p[j] \end{cases}
     * f[i][j]={
     * f[i−1][j−1],
     * false,
     * ​
     *
     * s[i]=p[j]
     * s[i]
     * 
     * =p[j]
     * ​
     *
     * 也就是说，如果 sss 的第 iii 个字符与 ppp 的第 jjj 个字符不相同，那么无法进行匹配；否则我们可以匹配两个字符串的最后一个字符，完整的匹配结果取决于两个字符串前面的部分。
     *
     * 如果 ppp 的第 jjj 个字符是 *，那么就表示我们可以对 ppp 的第 j−1j-1j−1 个字符匹配任意自然数次。在匹配 000 次的情况下，我们有
     *
     * f[i][j]=f[i][j−2]f[i][j] = f[i][j - 2]
     * f[i][j]=f[i][j−2]
     * 也就是我们「浪费」了一个字符 + 星号的组合，没有匹配任何 sss 中的字符。
     *
     * 在匹配 1,2,3,⋯1,2,3, \cdots1,2,3,⋯ 次的情况下，类似地我们有
     *
     * f[i][j]=f[i−1][j−2],if s[i]=p[j−1]f[i][j]=f[i−2][j−2],if s[i−1]=s[i]=p[j−1]f[i][j]=f[i−3][j−2],if s[i−2]=s[i−1]=s[i]=p[j−1]⋯⋯\begin{aligned} & f[i][j] = f[i - 1][j - 2], \quad && \text{if~} s[i] = p[j - 1] \\ & f[i][j] = f[i - 2][j - 2], \quad && \text{if~} s[i - 1] = s[i] = p[j - 1] \\ & f[i][j] = f[i - 3][j - 2], \quad && \text{if~} s[i - 2] = s[i - 1] = s[i] = p[j - 1] \\ & \cdots\cdots & \end{aligned}
     * ​
     *
     * f[i][j]=f[i−1][j−2],
     * f[i][j]=f[i−2][j−2],
     * f[i][j]=f[i−3][j−2],
     * ⋯⋯
     * ​
     *
     * ​
     *
     * if s[i]=p[j−1]
     * if s[i−1]=s[i]=p[j−1]
     * if s[i−2]=s[i−1]=s[i]=p[j−1]
     * ​
     *
     * 如果我们通过这种方法进行转移，那么我们就需要枚举这个组合到底匹配了 sss 中的几个字符，会增导致时间复杂度增加，并且代码编写起来十分麻烦。我们不妨换个角度考虑这个问题：字母 + 星号的组合在匹配的过程中，本质上只会有两种情况：
     *
     * 匹配 sss 末尾的一个字符，将该字符扔掉，而该组合还可以继续进行匹配；
     *
     * 不匹配字符，将该组合扔掉，不再进行匹配。
     *
     * 如果按照这个角度进行思考，我们可以写出很精巧的状态转移方程：
     *
     * f[i][j]={f[i−1][j] or f[i][j−2],s[i]=p[j−1]f[i][j−2],s[i]≠p[j−1]f[i][j] = \begin{cases} f[i - 1][j] \text{~or~} f[i][j - 2], & s[i] = p[j - 1] \\ f[i][j - 2], & s[i] \neq p[j - 1] \end{cases}
     * f[i][j]={
     * f[i−1][j] or f[i][j−2],
     * f[i][j−2],
     * ​
     *
     * s[i]=p[j−1]
     * s[i]
     * 
     * =p[j−1]
     * ​
     *
     * 在任意情况下，只要 p[j]p[j]p[j] 是 .，那么 p[j]p[j]p[j] 一定成功匹配 sss 中的任意一个小写字母。
     *
     * 最终的状态转移方程如下：
     *
     * f[i][j]={if (p[j]≠ ‘*’)={f[i−1][j−1],matches(s[i],p[j])false,otherwiseotherwise={f[i−1][j] or f[i][j−2],matches(s[i],p[j−1])f[i][j−2],otherwisef[i][j] = \begin{cases} \text{if~} (p[j] \neq \text{~`*'}) = \begin{cases} f[i - 1][j - 1], & \textit{matches}(s[i], p[j])\\ \text{false}, & \text{otherwise} \end{cases} \\ \text{otherwise} = \begin{cases} f[i - 1][j] \text{~or~} f[i][j - 2], & \textit{matches}(s[i], p[j-1]) \\ f[i][j - 2], & \text{otherwise} \end{cases} \end{cases}
     * f[i][j]=
     * ⎩
     * ⎨
     * ⎧
     * ​
     *
     * if (p[j]
     * 
     * = ‘*’)={
     * f[i−1][j−1],
     * false,
     * ​
     *
     * matches(s[i],p[j])
     * otherwise
     * ​
     *
     * otherwise={
     * f[i−1][j] or f[i][j−2],
     * f[i][j−2],
     * ​
     *
     * matches(s[i],p[j−1])
     * otherwise
     * ​
     *
     * ​
     *
     * 其中 matches(x,y)\textit{matches}(x, y)matches(x,y) 判断两个字符是否匹配的辅助函数。只有当 yyy 是 . 或者 xxx 和 yyy 本身相同时，这两个字符才会匹配。
     *
     * 细节
     *
     * 动态规划的边界条件为 f[0][0]=truef[0][0] = \text{true}f[0][0]=true，即两个空字符串是可以匹配的。最终的答案即为 f[m][n]f[m][n]f[m][n]，其中 mmm 和 nnn 分别是字符串 sss 和 ppp 的长度。由于大部分语言中，字符串的字符下标是从 000 开始的，因此在实现上面的状态转移方程时，需要注意状态中每一维下标与实际字符下标的对应关系。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solutions/521347/zheng-ze-biao-da-shi-pi-pei-by-leetcode-s3jgn/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @param p
     * @return
     */

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        // 边界
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        String s = "abbccmoodl.n";
        String p = "ab*f*cc.o*.*";
        SimpleReg reg = new SimpleReg();
        System.out.println(reg.isMatch(s, p));
    }

//
//    Queue<String> pQueue;
//
//    public SimpleReg() {
//        this.pQueue = new ArrayDeque<>();
//    }
//
//    /**
//     * @param s 字符串
//     * @param p 表达式
//     * @return
//     */
//    public boolean isMatch(String s, String p) {
//        genPQueue(p);
//        System.out.println(pQueue);
//
//        String poll = pQueue.poll();
//        for (int j = 0; j < s.length(); j++) {
//            if (poll == null) {
//                return false;
//            }
//
//            if (!poll.contains("*")) {
//                if (!(poll.equals(String.valueOf(s.charAt(j))) || ".".equals(poll))) {
//                    return false;
//                }
//            } else {
//                char pollHead = poll.charAt(0);
//                if (pollHead != '.') {
//                    if (pollHead == s.charAt(j)) {
//                        while (j < s.length()) {
//                            if (pollHead == s.charAt(j)) {
//                                j++;
//                            } else {
//                                j--;
//                                break;
//                            }
//                        }
//                    } else {
//                        poll = pQueue.poll();
//                        continue;
//                    }
//                } else {
//                    poll = pQueue.poll();
//                    if (".".equals(poll)) {
//                        continue;
//                    } else {
//                        while (j < s.length() && poll.charAt(0) != s.charAt(j)) {
//                            j++;
//                        }
//                    }
//                    continue;
//                }
//            }
//            poll = pQueue.poll();
//        }
//
//        return true;
//    }
//
//    /**
//     * 构建正则队列，a* 划为一组
//     *
//     * @param p
//     * @return
//     */
//    private void genPQueue(String p) {
//        char[] pChars = p.toCharArray();
//        int len = pChars.length;
//        for (int i = 0; i < len; i++) {
//            if (i != len - 1 && pChars[i + 1] == '*') {
//                pQueue.add(String.valueOf(pChars[i]) + pChars[i + 1]);
//                i++;
//            } else {
//                pQueue.add(String.valueOf(pChars[i]));
//            }
//        }
//    }



}
