package cloud.spring.my.study.concurrentdemo;

public class CorrectThreadLocalUsage {
    private static ThreadLocal<Integer> threadLocalVariable = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        // 创建多个线程访问线程本地变量
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                int localValue = threadLocalVariable.get();
                System.out.println("ThreadLocal value: " + localValue);
                threadLocalVariable.set(localValue + 1);
            });
            thread.start();
        }
    }
}

