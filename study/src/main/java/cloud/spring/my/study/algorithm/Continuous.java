package cloud.spring.my.study.algorithm;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Continuous {

    private static boolean flushCheck(int[] numbers) {
        Arrays.sort(numbers);

        // 没有0 差太大；/有四个0
        if ((numbers[0] != 0 && numbers[4] - numbers[0] > 4) || numbers[4] == 0) {
            return false;
        }

        int min = numbers[4];
        int max = numbers[0];
        for (int i = 0; i < 5; i++) {
            if (numbers[i] != 0) {
                if (i < 4 && numbers[i] == numbers[i + 1]) {
                    return false;
                }
                min = Math.min(min, numbers[i]);
                max = Math.max(max, numbers[i]);
            }
        }

        if (max - min >= 5) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        // 02346
        int[] numbers = {6, 0, 2, 0, 4};
        System.out.println(flushCheck(numbers));
    }
}
