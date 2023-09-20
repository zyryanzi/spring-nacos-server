package cloud.spring.my.study.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DistributeCandy {

    public DistributeCandy() {

    }

    public int distributeCandies(int[] candyType) {
        int length = candyType.length / 2;
        Set<Integer> totalCandyType = Arrays.stream(candyType).boxed().collect(Collectors.toSet());
        return Math.min(length, totalCandyType.size());
    }

    public static void main(String[] args) {
        int[] candyType = {3,3,3,3,3,3,3,15,17,19};
        DistributeCandy distributeCandy = new DistributeCandy();
        System.out.println(distributeCandy.distributeCandies(candyType));
    }

}
