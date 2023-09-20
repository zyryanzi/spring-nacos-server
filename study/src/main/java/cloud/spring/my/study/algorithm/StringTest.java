package cloud.spring.my.study.algorithm;

import java.util.*;

public class StringTest {

    private static List<Integer> data = new ArrayList<>();
    private static Set<Integer> res = new TreeSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            data.add(in.nextInt());
            if (data.size() > 0 && data.get(0) + 1 == data.size()) {
                Integer num = data.get(0);
                for (int i = 1; i < num + 1; i++) {
                    res.add(data.get(i));
                }
                res.stream().forEach(System.out::println);
                break;
            }
        }
    }

}
