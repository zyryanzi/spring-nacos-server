package cloud.spring.my.study.algorithm;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CheckPassword {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String res;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        boolean checkSub = false;
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String data = in.next();
            if (data.length() < 8 || data.contains("\\ ") || data.contains("\\n")) {
                System.out.println("NG");
                continue;
            }
            for (char item : data.toCharArray()) {
                if ((item >= 'a') && (item <= 'z')) {
                    a = 1;
                } else if ((item >= 'A') && (item <= 'Z')) {
                    b = 1;
                } else if (item >= '0' && item <= '9') {
                    c = 1;
                } else {
                    d = 1;
                }
            }
            checkSub = checkDupSub(data);
            res = a + b + c + d > 2 && checkSub ? "OK" : "NG";
            System.out.println(res);
        }

    }

    private static boolean checkDupSub(String data) {
        int length = data.length();
        Set<String> subSet = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = i; j < length - 2; j += 3) {
                if (!subSet.add(data.substring(j, j + 3))) {
                    return false;
                }
            }
        }
        return true;
    }

}
