package cloud.spring.my.study.domain;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Resource {

    private long num = 0L;

    private AtomicLong atomic = new AtomicLong(0L);

    private LongAdder adder = new LongAdder();

    private LongAccumulator accumulator = new LongAccumulator(Long::sum, 0L);

    public long getNum() {
        return num;
    }

    public long getAtomic() {
        return atomic.get();
    }

    public long getAdder() {
        return adder.sum();
    }

    public long getAccumulator() {
        return accumulator.get();
    }

    public synchronized void plusPlusBySync() {
        num++;
    }

    public void plusPlusByAtomic() {
        atomic.getAndIncrement();
    }

    public void plusPlusByAdder() {
        adder.increment();
    }

    public void plusPlusByAccumulator() {
        accumulator.accumulate(1L);
    }

}
