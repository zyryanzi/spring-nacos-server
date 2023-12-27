package cloud.spring.my.study.concurrentdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DeadLock {

    Object a = new Object();
    Object b = new Object();

    public void methodA() {
        synchronized (a) {
            log.info("--- {} 锁定资源 a ", Thread.currentThread().getName());
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            synchronized (b) {
                log.info("--- {} 锁定资源 b ", Thread.currentThread().getName());
            }
        }
    }

    public void methodB() {
        synchronized (b) {
            log.info("--- {} 锁定资源 b ", Thread.currentThread().getName());
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            synchronized (a) {
                log.info("--- {} 锁定资源 a ", Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::methodA, "ta").start();
        new Thread(deadLock::methodB, "tb").start();
    }

}
