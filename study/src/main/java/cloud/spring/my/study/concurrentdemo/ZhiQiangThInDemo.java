package cloud.spring.my.study.concurrentdemo;

import java.util.concurrent.TimeUnit;

public class ZhiQiangThInDemo extends Thread {
    @Override
    public void run() {
        try {
            // 模拟耗时操作
            for (int i = 1; i <= 10; i++) {
                System.out.println("Count: " + i);
                Thread.sleep(1000);  // 线程休眠1秒钟
                if (Thread.interrupted()) {
                    // 检查是否收到中断信号
                    System.out.println("Thread interrupted, stopping...");
                    return;
                }
            }
        } catch (InterruptedException e) {
            // 处理中断异常
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZhiQiangThInDemo zhiQiangThInDemo = new ZhiQiangThInDemo();
        Thread t1 = new Thread(zhiQiangThInDemo::run, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(t1::interrupt, "t2").start();

    }
}


