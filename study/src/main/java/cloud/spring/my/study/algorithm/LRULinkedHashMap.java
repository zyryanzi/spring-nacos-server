package cloud.spring.my.study.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 最简单的LRU
 *
 * @param <K>
 * @param <V>
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Lock lock = new ReentrantLock();

    public LRULinkedHashMap(int maxCapacity) {
        super(maxCapacity, DEFAULT_LOAD_FACTOR, Boolean.TRUE);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            super.clear();
        } finally {
            lock.unlock();
        }
    }

    public Collection<Map.Entry<K, V>> getAll() {
        try {
            lock.lock();
            return new ArrayList<>(super.entrySet());
        } finally {
            lock.unlock();
        }
    }

}
