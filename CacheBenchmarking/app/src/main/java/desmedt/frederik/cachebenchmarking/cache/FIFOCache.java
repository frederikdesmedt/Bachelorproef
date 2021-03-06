package desmedt.frederik.cachebenchmarking.cache;

import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * A simple FIFO cache replacement policy implementation. Uses the FIFO mode of {@link LinkedHashMap}
 * to store, retrieve and remove its elements.
 */
public class FIFOCache<K extends Comparable<K>, V> implements Cache<K, V> {

    public static final String CACHE_TAG = "FifoCache";

    private LinkedHashMap<K, V> heap = new LinkedHashMap<>();
    private int maxSize = 0;

    public FIFOCache(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public V get(K key) {
        return heap.get(key);
    }

    @Override
    public void put(K key, V value) {
        heap.put(key, value);

        if (maxSize < heap.size()) {
            removeElement();
        }
    }

    private void removeElement() {
        K key = heap.keySet().iterator().next();
        heap.remove(key);
    }

    @Override
    public void remove(K key) {
        heap.remove(key);
    }

    @Override
    public void removeAll() {
        heap.clear();
    }

    @Override
    public int maxSize() {
        return maxSize;
    }

    @Override
    public int size() {
        return heap.size();
    }
}
