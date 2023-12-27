package cloud.spring.my.study.concurrentdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized-wait-notify,
 * lock-condition-await-signal,
 * LockSupport-park-unPark;
 */
public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
//        SyncWN syncWNClient = new SyncWN();
//        syncWNClient.syncWN();
//        LCAwS lcAwS = new LCAwS();
//        lcAwS.lCAwS();
        LSPUnP lspUnP = new LSPUnP();
        lspUnP.lSPUnP();
    }

}

/**
 * 1. wait 和 notify 方向不可反，否则会错过，然后一直wait;
 * 2. 如果没有加同步块，会报 IllegalMonitorStateException, 这个异常是因为：
 * 尝试在非同步方法或代码块之外调用Object类的wait、notify或notifyAll方法时抛出;
 */
@Slf4j
class SyncWN {
    public void syncWN() throws InterruptedException {
        LockSupportDemo objLock = new LockSupportDemo();
        new Thread(() -> {
            synchronized (objLock) {
                try {
                    log.info("-->> 进入线程：{}", Thread.currentThread().getName());
                    objLock.wait();
                    log.info("-->> {} 被唤醒", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (objLock) {
                objLock.notify();
                log.info("-->> {} 发出通知", Thread.currentThread().getName());
            }
        }, "t2").start();
    }
}

/**
 * 1. await 和 signal 方向不可反，否则会错过，然后一直 await;
 * 2. 如果没有加锁，会报 IllegalMonitorStateException, 这个异常是因为：
 * 尝试在非同步方法或代码块之外调用 Object 类的 await、signal 或 signalAll 方法时抛出;
 */
@Slf4j
class LCAwS {
    public void lCAwS() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                log.info("-->> 进入线程：{}", Thread.currentThread().getName());
                condition.await();
                log.info("-->> {} 被唤醒", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                log.info("-->> {} 发出通知", Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}

/**
 * 1. 无锁的要求
 * 2. 先唤醒再等待也可以
 * 3. 通行证 permit 只有一个
 */
@Slf4j
class LSPUnP {
    public void lSPUnP() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.info("-->> 进入线程：{}", Thread.currentThread().getName());
            LockSupport.park();
            log.info("-->> {} 被唤醒", Thread.currentThread().getName());
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            LockSupport.unpark(t1);
            log.info("-->> {} 发出通知", Thread.currentThread().getName());
        }, "t2").start();
    }
}
