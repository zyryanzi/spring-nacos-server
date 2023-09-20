package cloud.spring.my.study.algorithm;

public class IntToRomanPlus {

    private static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num > values[i]){
                num -= values[i];
                roman.append(symbols[i]);
            }
            if (num == 0) {
                break;
            }
        }

        return roman.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
    }

}
