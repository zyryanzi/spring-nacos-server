package cloud.spring.my.common.utils;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 异步任务工具类
 *
 */
class CompletableFutureTaskUtil extends CompletableFutureUtil {

    /**
     * 线程池
     */
    private Executor executor;

    /**
     * future 容器
     */
    private Map<String, CompletableFuture<?>> futureMap;

    public CompletableFutureTaskUtil() {
        //初始化futureMap
        generateFutureMap();
    }

    public CompletableFutureTaskUtil(Executor executor) {
        //赋值线程池
        if (ObjectUtil.isNotNull(executor)) {
            this.executor = executor;
        }
        //初始化futureMap
        generateFutureMap();
    }

    /**
     * 等待所有任务完成
     */
    @SneakyThrows
    @Override
    public void allOf() {
        if (ObjectUtils.isNotEmpty(this.futureMap)) {
            CompletableFuture.allOf(this.futureMap.values().toArray(new CompletableFuture[0])).get();
        }
    }

    /**
     * 获取futureMap中的值
     *
     * @param key key
     * @return futureMap中的值
     */
    @SneakyThrows
    @Override
    public Object getMapValueByKey(String key) {
        if (ObjectUtils.isEmpty(this.futureMap)) {
            return new Object();
        }
        return this.futureMap.get(key).get();
    }

    /**
     * 关闭
     */
    @Override
    public void shutdown() {
        this.executor = null;
        this.futureMap = null;
    }

    /**
     * 异步执行
     *
     * @param supplier 异步执行
     * @return future
     */
    @Override
    public <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        CompletableFuture<U> future = this.getCompletableFutureSupplyAsync(supplier);
        futureMap.put(UUID.randomUUID().toString(), future);
        return future;
    }

    /**
     * 异步执行 指定容器键
     *
     * @param key      key
     * @param supplier 异步执行
     * @return future
     */
    @Override
    public <U> CompletableFuture<U> supplyAsync(String key, Supplier<U> supplier) {
        CompletableFuture<U> future = this.getCompletableFutureSupplyAsync(supplier);
        futureMap.put(Optional.ofNullable(key).filter(StrUtil::isNotBlank).orElse(UUID.randomUUID().toString()), future);
        return future;
    }

    /**
     * 异步执行后异步执行
     * @param from
     * @param function
     * @return
     * @param <F>
     * @param <U>
     */
    @Override
    public <F, U> CompletableFuture<F> thenApplyAsync(CompletableFuture<U> from, Function<? super U, ? extends F> function) {
        CompletableFuture<F> future = this.getCompletableFutureThenApplyAsync(from, function);
        futureMap.put(UUID.randomUUID().toString(), future);
        return future;
    }

    /**
     * 异步执行后异步执行 指定容器键
     * @param key
     * @param from
     * @param function
     * @return
     * @param <F>
     * @param <U>
     */
    @Override
    public <F, U> CompletableFuture<F> thenApplyAsync(String key, CompletableFuture<U> from, Function<? super U, ? extends F> function) {
        CompletableFuture<F> future = this.getCompletableFutureThenApplyAsync(from, function);
        futureMap.put(Optional.ofNullable(key).filter(StrUtil::isNotBlank).orElse(UUID.randomUUID().toString()), future);
        return future;
    }

    /**
     * 初始化 futureMap
     */
    private void generateFutureMap() {
        this.futureMap = MapUtil.newConcurrentHashMap();
    }

    /**
     * 获取异步执行结果
     *
     * @param supplier 异步执行
     * @return future
     */
    @NonNull
    private <U> CompletableFuture<U> getCompletableFutureSupplyAsync(Supplier<U> supplier) {
        CompletableFuture<U> future;
        if (ObjectUtil.isNotNull(this.executor)) {
            future = CompletableFuture.supplyAsync(supplier, this.executor);
        } else {
            future = CompletableFuture.supplyAsync(supplier);
        }
        return future;
    }

    /**
     * 获取异步执行后异步执行结果
     *
     * @param from
     * @param function
     * @return future
     */
    @NonNull
    private <F, U> CompletableFuture<F> getCompletableFutureThenApplyAsync(CompletableFuture<U> from, Function<? super U, ? extends F> function) {
        CompletableFuture<F> future;
        if (ObjectUtil.isNotNull(this.executor)) {
            future = from.thenApplyAsync(function, this.executor);
        } else {
            future = from.thenApplyAsync(function);
        }
        return future;
    }

}
