package cloud.spring.my.study.algorithm;

import java.util.HashMap;
import java.util.Map;

public class BracketMatch {

    private static Map<String, String> pair = new HashMap<>();
    static {
        pair.put("{", "}");
        pair.put("[", "]");
        pair.put("(", ")");
    }

    public static boolean bracketMatch(String str) {
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (ch == '(') {

            } else if (ch == '['){

            } else if (ch == '{') {

            }
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
