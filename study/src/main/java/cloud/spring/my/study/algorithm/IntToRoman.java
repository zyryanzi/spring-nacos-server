package cloud.spring.my.study.algorithm;

import java.util.HashMap;
import java.util.Map;

public class IntToRoman {

    private static Map<Integer, String> gradeMap;

    static {
        gradeMap = new HashMap<>();
        gradeMap.put(1, "I");
        gradeMap.put(2, "V");//
        gradeMap.put(3, "X");
        gradeMap.put(4, "L");//
        gradeMap.put(5, "C");
        gradeMap.put(6, "D");//
        gradeMap.put(7, "M");
        gradeMap.put(8, "N");//
    }

    public static String intToRoman(int num) {
        // 计算千 7
        int th = num % 10000 / 1000;
        String thousand = calc(th, 7);

        // 计算百
        int hu = num % 1000 / 100;
        String hundred = calc(hu, 6);

        // 计算十
        int te = num % 100 / 10;
        String ten = calc(te, 4);

        // 计算个
        int un = num % 10;
        String unit = calc(un, 2);

        return thousand + hundred + ten + unit;
    }


    private static String calc(int num, int grade) {
        if (num == 0) {
            return "";
        }
        boolean need5 = num >= 5;
        int mod5 = num % 5;
        boolean modMoreThan3 = mod5 > 3;
        return fillNote(grade, num, modMoreThan3, need5, mod5);
    }

    private static String fillNote(int grade, int num, boolean modMoreThan3, boolean need5, int mod5) {
        String res = "";
        if (grade == 7) {
            for (int i = 0; i < num; i++) {
                res += gradeMap.get(grade);
            }
            return res;
        }

        if (modMoreThan3) {
            if (need5) {
                res = gradeMap.get(grade - 1) + gradeMap.get(grade + 1);
            } else {
                res = gradeMap.get(grade - 1) + gradeMap.get(grade);
            }
        } else {
            if (need5) {
                res = gradeMap.get(grade);
            }
            for (int i = 0; i < mod5; i++) {
                res += gradeMap.get(grade - 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
    }

}
