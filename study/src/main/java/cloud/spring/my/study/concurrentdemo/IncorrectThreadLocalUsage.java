package cloud.spring.my.study.concurrentdemo;

public class IncorrectThreadLocalUsage {
    private static ThreadLocal<Integer> globalVariable = new ThreadLocal<>();

    public static void main(String[] args) {
        globalVariable.set(10); // 设置全局变量

        // 创建多个线程访问全局变量
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                // 错误地访问全局变量
                System.out.println("ThreadLocal value: " + globalVariable.get());
                globalVariable.set(globalVariable.get() + 1);
            });
            thread.start();
        }
    }
}


