package cloud.spring.my.study.concurrentdemo;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 对四种原子操作进行性能对比，i++
 * synchronized, Atomic, LongAdder, LongAccumulator
 */
@Slf4j
public class FourAtomicComparisonOri {

    private static final int ACC_NUM = 100 * 10000;


    public static void main(String[] args) throws InterruptedException {
        ResourceOri resource = new ResourceOri();

        CountDownLatch latch1 = new CountDownLatch(50);
        CountDownLatch latch2 = new CountDownLatch(50);
        CountDownLatch latch3 = new CountDownLatch(50);
        CountDownLatch latch4 = new CountDownLatch(50);

        long start;
        long end;

        start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < ACC_NUM; j++) {
                        resource.plusPlusBySync();
                    }
                } finally {
                    latch1.countDown();
                }
            }).start();
        }
        latch1.await();
        end = System.currentTimeMillis();
        log.info("--->> by {}, cost time: {}, result: {}", "synchronized", end - start, resource.num);

        start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < ACC_NUM; j++) {
                        resource.plusPlusByAtomic();
                    }
                } finally {
                    latch2.countDown();
                }
            }).start();
        }
        latch2.await();
        end = System.currentTimeMillis();
        log.info("--->> by {}, cost time: {}, result: {}", "atomic", end - start, resource.atomic.get());


        start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < ACC_NUM; j++) {
                        resource.plusPlusByAdder();
                    }
                } finally {
                    latch3.countDown();
                }
            }).start();
        }
        latch3.await();
        end = System.currentTimeMillis();
        log.info("--->> by {}, cost time: {}, result: {}", "adder", end - start, resource.adder.sum());

        start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < ACC_NUM; j++) {
                        resource.plusPlusByAccumulator();
                    }
                } finally {
                    latch4.countDown();
                }
            }).start();
        }
        latch4.await();
        end = System.currentTimeMillis();
        log.info("--->> by {}, cost time: {}, result: {}", "accumulator", end - start, resource.accumulator.get());

    }

}


class ResourceOri {

    int num = 0;

    AtomicLong atomic = new AtomicLong(0L);

    LongAdder adder = new LongAdder();

    LongAccumulator accumulator = new LongAccumulator(Long::sum, 0L);

    public synchronized void plusPlusBySync() {
        num++;
    }

    public  void plusPlusByAtomic() {
        atomic.getAndIncrement();
    }

    public  void plusPlusByAdder() {
        adder.increment();
    }

    public  void plusPlusByAccumulator() {
        accumulator.accumulate(1L);
    }

}


