package cloud.spring.my.study.algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class MovingAverage {
    private int size;
    private Queue<Integer> window;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.window = new ArrayDeque<>(size);
    }

    public double next(int val) {
        if (this.window.size() == this.size) {
            this.window.poll();
        }
        this.window.offer(val);
        return (double) (window.stream().reduce(Integer::sum).orElse(0)) / this.window.size();
    }

    public static void main(String[] args) {
        int[] data = {1,4,36,47,5,86,72,8};
        MovingAverage obj = new MovingAverage(3);
        Arrays.stream(data).forEach(i -> System.out.println(obj.next(i)));
    }

}
