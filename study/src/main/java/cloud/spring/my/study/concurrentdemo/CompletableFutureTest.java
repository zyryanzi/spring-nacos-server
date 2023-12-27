package cloud.spring.my.study.concurrentdemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "hello, cf");

        log.info(cf.join());
    }

}
