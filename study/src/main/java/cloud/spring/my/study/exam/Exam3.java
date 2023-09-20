package cloud.spring.my.study.exam;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 按照环保公司要求，小明需要在沙化严重的地区进行植树防沙工作，初步目标是种植一条直线的树带。由于有些区域目前不适合种植树木，所以只能在一些可以种植的点来种植树木
 *
 * 在树苗有限的情况下，要达到最佳效果，就要尽量散开种植，不同树苗之间的最小间距要尽量大。给你一个适合种情树木的点坐标和一个树苗的数量，请帮小明选择一个最佳的最小种植间距。
 *
 * 例如，适合种植树木的位置分别为1,3,5,6,7,10,13 树苗数量是3，种植位置在1,7,13，树苗之间的间距都是6，均匀分开，就达到了散开种植的目的，最佳的最小种植间距是6
 *
 * 输入描述
 * 第1行表示适合种树的坐标数量
 * 第2行是适合种树的坐标位置
 * 第3行是树苗的数量
 *
 * 例如：
 *
 * 7
 * 1 5 3 6 10 7 13
 * 3
 *
 * 输出描述
 * 最佳的最小种植间距
 */
public class Exam3 {

    public static int minDistance(int[] pos, int treeNum) {
        if (treeNum < 2) {
            return -1;
        }
        int posNum = pos.length;
        Arrays.sort(pos);
        for (int i = 2; i < posNum; i++) {
            int avg = (pos[posNum - 1] - pos[0]) / i - 1;


        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int posNum = in.nextInt();
            int[] pos = new int[posNum];
            for (int i = 0; i < posNum; i++) {
                pos[i] = in.nextInt();
            }
            int treeNum = in.nextInt();

            System.out.println(minDistance(pos, treeNum));
            break;
        }
    }

}
