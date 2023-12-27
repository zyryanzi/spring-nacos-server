package cloud.spring.my.study.concurrentdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantReadWriteLockDemo {

    Map<String, String> map = new HashMap<>();

    Lock lock = new ReentrantLock();

    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String k, String v) {
        lock.lock();
        try {
            log.info("--->> 线程 {} 正在写入", Thread.currentThread().getName());
            map.put(k, v);
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void read(String k) {
        lock.lock();
        try {
            String result = map.get(k);
            TimeUnit.MILLISECONDS.sleep(200);
            log.info("--->> 线程 {} 读取结果：{}", Thread.currentThread().getName(), result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo resource = new ReentrantReadWriteLockDemo();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                resource.write(finalI + "", finalI + "");
            }, "thread-" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                resource.read(finalI + "");
            }, "thread-" + i).start();
        }
    }

}
