package cloud.spring.my.study.concurrentdemo;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.DoubleAccumulator;

@Slf4j
public class AtomicFieldUpdaterDemo {

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    account.init(account);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
//                        account.plusPlus();
                        account.atomicPlusPlus(account);
                    }
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await();

        log.info("--->> money: {}", account.getMoney());

    }

}

/**
 * 资源类
 */
@Data
@Slf4j
class BankAccount {

    private String bankName = "CCB";

    // public volatile
    public volatile int money = 0;

    static final AtomicIntegerFieldUpdater<BankAccount> moneyUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    public volatile Boolean isInit = Boolean.FALSE;

    static final AtomicReferenceFieldUpdater<BankAccount, Boolean> isInitUpdater =
            AtomicReferenceFieldUpdater.newUpdater(BankAccount.class, Boolean.class, "isInit");

    public void init(BankAccount bankAccount) throws InterruptedException {
        if (isInitUpdater.compareAndSet(bankAccount, Boolean.FALSE, Boolean.TRUE)) {
            log.info("--->> thread {} init BankAccount", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
        } else {
            log.info("--->> BankAccount already initiated by others");
        }
    }

    public void plusPlus() {
        money++;
    }

    public void atomicPlusPlus(BankAccount bankAccount) {
        moneyUpdater.getAndIncrement(bankAccount);
    }

}