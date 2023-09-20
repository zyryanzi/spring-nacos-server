package cloud.spring.my.study.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class CountLeast {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String data = in.next();
            if (data == null || "".equals(data)) {
                System.out.println(data);
                break;
            }

            // 使用字符做下标，统计出所有字符出现次数
            int[] times = new int[26];
            char[] dataChArr = data.toCharArray();
            for (char ch : dataChArr) {
                times[ch - 'a'] += 1;
            }

            // 最少次，极端情况所有字符都一样
            int minTime = data.length();

            // 找出出现最少次数的字符，可能多个
            List<Integer> minTimeChList = new ArrayList<>();
            for (int i = 0; i < times.length; i++) {
                if (times[i] == 0 || times[i] > minTime) {
                    continue;
                }
                if (times[i] < minTime) {
                    minTime = times[i];
                    minTimeChList = new ArrayList<>();
                    minTimeChList.add(i + 'a');
                } else if (times[i] == minTime) {
                    minTimeChList.add(i + 'a');
                }
            }

            // 替换掉出现最少次的字符
            List<Integer> finalMinTimeChList = minTimeChList;
            String res = data.chars()
                    .filter(c -> !finalMinTimeChList.contains(c))
                    .mapToObj(ch -> String.valueOf((char) ch))
                    .collect(Collectors.joining());

            System.out.println(res);
            break;
        }
    }

}
