package cloud.spring.my.study.concurrentdemo;

import cloud.spring.my.study.domain.Resource;
import cloud.spring.my.study.gof23.behaviorPatten.state.statemachine.handler.IStateHandler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {

    static volatile boolean isSleep = true;

    static ThreadLocal<Parameters> threadLocal = ThreadLocal.withInitial(Parameters::new);

    public static void main(String[] args) throws InterruptedException {


        new Thread(() -> {
            Parameters parameters = new Parameters();
            parameters.setGetNumMethodName("t1Method");
            threadLocal.set(parameters);
            Parameters parameters1 = threadLocal.get();
            System.out.println("--->> in t1 " + parameters1.getGetNumMethodName());
            while (isSleep) {

            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(2);

        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Thread thread : allStackTraces.keySet()) {
            if (thread.getName().equals("t1")) {
                Thread.State state = thread.getState();
                System.out.println("--->> in t2 " + state);
                isSleep = false;
            }
        }

    }

}
