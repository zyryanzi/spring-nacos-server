package cloud.spring.my.study.algorithm;

import java.util.Arrays;

public class CountPrimes {

    public static int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }

        int[] cntArr = new int[n];
        Arrays.fill(cntArr, 1);

        for (int i = 2; i * i < n; i++) {
            for (int j = 2; i * j < n; j++) {
                cntArr[i * j] = 0;
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += cntArr[i];
        }
        // 0,1不算
        return cnt - 2;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(30));
    }
}
