package cloud.spring.my.study.algorithm;

public class MaxProfit {

    /**
     * 因为同一天可以操作买卖多次，只要保证手里一股即可
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 2, 10};
        System.out.println(maxProfit(prices));
    }

}
