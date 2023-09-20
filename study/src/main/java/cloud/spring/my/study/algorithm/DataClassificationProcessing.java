package cloud.spring.my.study.algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class DataClassificationProcessing {

    public static String dfp(int[] data, int[] pats) {
        int[] distPats = Arrays.stream(pats).distinct().toArray();
        Arrays.sort(distPats);

        List<Integer> res = new ArrayList<>();

        for (int pat : distPats) {
            Map<Integer, Integer> matched = match(data, pat);
            int size = matched.size();
            if (size > 0) {
                res.add(pat);
                res.add(size);
                matched.forEach((k, v) -> {
                    res.add(k);
                    res.add(v);
                });
            }
        }
        res.add(0, res.size());

        return res.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private static Map<Integer, Integer> match(int[] data, int pat) {
        Map<Integer, Integer> res = new LinkedHashMap<>();

        for (int i = 0; i < data.length; i++) {
            if (String.valueOf(data[i]).contains(String.valueOf(pat))) {
                res.put(i, data[i]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int dataNum = in.nextInt();
            int[] data = new int[dataNum];
            for (int i = 0; i < dataNum; i++) {
                data[i] = in.nextInt();
            }
            int patsNum = in.nextInt();
            int[] pats = new int[patsNum];
            for (int i = 0; i < patsNum; i++) {
                pats[i] = in.nextInt();
            }
            System.out.println(dfp(data, pats));
            break;
        }
    }
}
