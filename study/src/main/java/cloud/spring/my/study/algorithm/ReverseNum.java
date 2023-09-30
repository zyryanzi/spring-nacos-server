package cloud.spring.my.study.algorithm;

/**
 * 反转整形数字
 */
public class ReverseNum {

    public static long reverseInt(int num) {
        boolean isPlus = true; // 正数
        if (num < 0) {
            isPlus = false;
            num = -num;
        }

        long res = 0;
        while (num > 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }

        return isPlus ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(reverseInt(0));
    }

}
