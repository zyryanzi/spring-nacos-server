package cloud.spring.my.study.concurrentdemo;

import cloud.spring.my.study.domain.Resource;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 对四种原子操作进行性能对比，i++
 * synchronized, Atomic, LongAdder, LongAccumulator
 */
@Slf4j
public class FourAtomicComparison {

    private static Resource resource = new Resource();

    private static final int ACC_NUM = 100 * 10000;

    private static final List<Parameters> paramsList = Lists.newArrayList();

    static {
        try {
            Parameters parameters1 = new Parameters();
            parameters1.setResource(resource);
            parameters1.setExecMethodName("plusPlusBySync");
            parameters1.setGetNumMethodName("getNum");
            paramsList.add(parameters1);
            Parameters parameters2 = parameters1.clone();
            parameters2.setExecMethodName("plusPlusByAtomic");
            parameters2.setGetNumMethodName("getAtomic");
            paramsList.add(parameters2);
            Parameters parameters3 = parameters1.clone();
            parameters3.setExecMethodName("plusPlusByAdder");
            parameters3.setGetNumMethodName("getAdder");
            paramsList.add(parameters3);
            Parameters parameters4 = parameters1.clone();
            parameters4.setExecMethodName("plusPlusByAccumulator");
            parameters4.setGetNumMethodName("getAccumulator");
            paramsList.add(parameters4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        paramsList.forEach(parameters -> {
            try {
                execTest(parameters.getResource(), parameters.getExecMethodName(), parameters.getGetNumMethodName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    private static void execTest(Resource resource, String execMethod, String getMethod)
            throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method plusMethod = Resource.class.getMethod(execMethod);
        Method getNumMethod = Resource.class.getMethod(getMethod);

        CountDownLatch latch = new CountDownLatch(50);

        long start = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < ACC_NUM; j++) {
                        plusMethod.invoke(resource);
                    }
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        long end = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        log.info("--->> by {}, cost time: {}, result: {}", execMethod, end - start, getNumMethod.invoke(resource));
    }

}

@Data
class Parameters implements Cloneable {
    private Resource resource;
    private String execMethodName;
    private String getNumMethodName;

    @Override
    protected Parameters clone() throws CloneNotSupportedException {
        return (Parameters) super.clone();
    }
}




