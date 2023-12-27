package cloud.spring.my.study.concurrentdemo;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicDemo {

    private AtomicInteger atomicInt = new AtomicInteger();

    private Integer normalInt = 0;

    public int getAtomicInt() {
        return atomicInt.get();
    }

    public Integer getNormalInt() {
        return normalInt;
    }

    public synchronized void normalIPP() {
        this.normalInt++;
    }

    public void atomicIPP() {
        this.atomicInt.getAndIncrement();
    }

    public static void main(String[] args) {
        AtomicDemo atomicDemo1 = new AtomicDemo();
        AtomicDemo atomicDemo2 = new AtomicDemo();

        long start = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    atomicDemo1.normalIPP();
                    if (atomicDemo1.getNormalInt() == 10000000) {
                        log.info("--->>> normalInt: {}", atomicDemo1.getNormalInt());
                        log.info("--->>> synchronized during time: {}",
                                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - start);
                    }
                }
            }).start();

            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    atomicDemo2.atomicIPP();
                    if (atomicDemo2.getAtomicInt() == 10000000) {
                        log.info("--->>> atomicInt: {}", atomicDemo2.getAtomicInt());
                        log.info("--->>> atomic during time: {}",
                                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli() - start);
                    }
                }
            }).start();
        }


    }

}
