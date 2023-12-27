package cloud.spring.my.study.tricks;


import cloud.spring.my.study.concurrentdemo.CompletableFutureTest;

import java.util.concurrent.locks.ReentrantLock;

public class T {

    public static void main(String[] args) {
        System.out.println(testFinally());
    }

    public static int testFinally() {
        try {
            return 1;
        } finally {
            // 在finally块中的return语句会覆盖try块中的return语句
            return 2;
        }
    }


}