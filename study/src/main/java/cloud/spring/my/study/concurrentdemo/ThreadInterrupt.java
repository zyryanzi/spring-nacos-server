package cloud.spring.my.study.concurrentdemo;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程中断,可以用 volatile 也可以用 atomic
 */
@Slf4j
@Data
public class ThreadInterrupt {

    /**
     * 不加 volatile 似乎也可以？？？
     */
    private volatile boolean stop = false;

    private AtomicBoolean atomicStop = new AtomicBoolean(false);

    public String worker() {
        while (true) {
//            if (this.stop) {
//            if (atomicStop.get()) {
            if (Thread.currentThread().isInterrupted()) {
                log.info("-->> 线程 {} 被通知停止", Thread.currentThread().getName());
                break;
            }
            log.info("-->> 线程 {} 正在运行...", Thread.currentThread().getName());
        }
        String threadName = Thread.currentThread().getName();
        log.info("-->> worker threadName: {}", threadName);
        return threadName;
    }

    public void terminator(List<String> threadNames) {
        log.info("-->> 线程 {} 发出停止通知", Thread.currentThread().getName());
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
//        this.stop = true;
//        atomicStop.set(true);
        threadNames.forEach(threadName -> {
            for (Thread thread : allStackTraces.keySet()) {
                log.info("-->> thread name: {}", thread.getName());
                if (thread.getName().equals(threadName)) {
                    thread.interrupt();
                }
            }
        });
    }
}

@Slf4j
class TIClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        ThreadInterrupt ti = new ThreadInterrupt();

        List<String> threadNames = Lists.newArrayList();

        CompletableFuture.runAsync(() -> {
            threadNames.add(Thread.currentThread().getName());
            ti.worker();
        }, executor);

        log.info("-->> threadNames: {}", threadNames);

        TimeUnit.MILLISECONDS.sleep(10);

        executor.execute(() -> ti.terminator(threadNames));

        executor.shutdown();
    }
}


