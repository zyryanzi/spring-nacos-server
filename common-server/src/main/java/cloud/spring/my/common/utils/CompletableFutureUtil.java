package cloud.spring.my.common.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 异步工具类
 *
 */
public abstract class CompletableFutureUtil {

    /**
     * 获取实例 对外 默认线程池
     *
     * @return CompletableFutureUtil
     */
    public static CompletableFutureUtil getInstance() {
        return createInstance();
    }

    /**
     * 获取实例 对外 指定线程池
     *
     * @return CompletableFutureUtil
     */
    public static CompletableFutureUtil getInstance(Executor executor) {
        return createInstance(executor);
    }

    /**
     * 私有构造 默认线程池
     *
     * @return CompletableFutureUtil
     */
    private static CompletableFutureUtil createInstance() {
        return new CompletableFutureTaskUtil();
    }

    /**
     * 私有构造 指定线程池
     *
     * @return CompletableFutureUtil
     */
    private static CompletableFutureUtil createInstance(Executor executor) {
        return new CompletableFutureTaskUtil(executor);
    }

    public abstract <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier);

    public abstract <U> CompletableFuture<U> supplyAsync(String key, Supplier<U> supplier);

    public abstract <F, U> CompletableFuture<F> thenApplyAsync(CompletableFuture<U> from, Function<? super U, ? extends F> function);

    public abstract <F, U> CompletableFuture<F> thenApplyAsync(String key, CompletableFuture<U> from, Function<? super U, ? extends F> function);

    /**
     * 等待所有任务完成
     */
    public abstract void allOf();

    public abstract Object getMapValueByKey(String key);

    public abstract void shutdown();

}

