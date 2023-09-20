package cloud.spring.my.study.algorithm;

public class MaxArea {

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int end = height.length - 1;
        int start = 0;
        while (end > start) {
            if (maxArea < (end - start) * Math.min(height[start], height[end])) {
                maxArea = (end - start) * Math.min(height[start], height[end]);
            }
            if (height[start] > height[end]) {
                end--;
            } else {
                start++;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(heights));
    }

}
