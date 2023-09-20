package cloud.spring.my.study.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 统计目标目录下大小
 * 目录个数 目标目录id
 * 目录id 内容文件大小 子目录列表
 *
 * 4 2
 * 4 20 ()
 * 5 30 ()
 * 2 10 (4,5)
 * 1 40 ()
 */
public class Exam2 {

    public static int volSum(int[] vol, List<List<String>> subIdList, int target) {
        if (subIdList.get(target).size() == 0) {
            return vol[target];
        }

        int res = vol[target];
        List<String> tSubList = subIdList.get(target);
        for (String subId : tSubList) {
            int sId = Integer.parseInt(subId);
            res += volSum(vol, subIdList, sId);
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int dirNum = in.nextInt();
            int tId = in.nextInt();
            if (dirNum < 1 || dirNum > 100 || tId < 1 || tId > 200) {
                System.out.println(-1);
                break;
            }
            // 使用ID 做下标
            List idList = new ArrayList();
            int[] vol = new int[200];
            List<List<String>> subIdList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                subIdList.add(new ArrayList<>());
            }
            for (int i = 0; i < dirNum; i++) {
                int id = in.nextInt();
                idList.add(id);
                vol[id] = in.nextInt();
                String subIds = in.next().replace("(", "").replace(")", "");
                if (!"".equals(subIds)) {
                    subIdList.add(id, Arrays.asList(subIds.split(",")));
                }
            }

            if (tId > 200 || tId < 0 || !idList.contains(tId)) {
                System.out.println(-1);
                break;
            }
            System.out.println(volSum(vol, subIdList, tId));

            break;
        }
    }

}
