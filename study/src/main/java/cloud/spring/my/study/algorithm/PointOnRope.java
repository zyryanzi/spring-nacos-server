package cloud.spring.my.study.algorithm;

public class PointOnRope {

    private static int pointOnRope(int[] arr, int ropeLen) {
        int pointOnRope = 0;
        int len = arr.length;
        if (arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[j] - arr[i] > ropeLen) {
                    break;
                }
                if (arr[j] - arr[i] <= ropeLen && j - i > pointOnRope) {
                    pointOnRope = j - i;
                }
            }
        }
        return pointOnRope + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2};
        System.out.println(pointOnRope(arr, 7));
    }
}
