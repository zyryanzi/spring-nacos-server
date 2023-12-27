package cloud.spring.my.study.concurrentdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    private AtomicReference<Thread> aRThread = new AtomicReference<>();

//    private Thread curThread;

    public void lock() {
        Thread curThread = Thread.currentThread();
        System.out.println("--->> thread" + curThread.getName() + " come into spin lock ");
        while (!aRThread.compareAndSet(null, curThread)) {

        }
        System.out.println("--->> thread " + curThread.getName() + " got spin lock");
    }

    public void unlock() {
        Thread curThread = Thread.currentThread();
        aRThread.compareAndSet(curThread, null);
        System.out.println("--->> unlock spin lock: " + curThread.getName());
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLock = new SpinLockDemo();

        new Thread(() -> {
            spinLock.lock();
            System.out.println("--->> exec t1");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            spinLock.lock();
            System.out.println("--->> exec t2");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }, "t2").start();
    }

}
