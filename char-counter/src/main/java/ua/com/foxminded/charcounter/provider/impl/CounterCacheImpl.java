    package ua.com.foxminded.charcounter.provider.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import ua.com.foxminded.charcounter.provider.CounterCacheProvider;

public class CounterCacheImpl <K, V> extends LinkedHashMap<K, V> implements CounterCacheProvider<K, V> {
    private static final long serialVersionUID = -8104996262495360891L;
    private final int maxEntries;
    private final Map<K, V> cache;
    
    public CounterCacheImpl(int maxEntries) {
        if (maxEntries <= 0) {
            throw new IllegalArgumentException("Size is 0 or less");
        }
        this.maxEntries = maxEntries;
        cache = new LinkedHashMap<>(maxEntries + 1, .75F, true);
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxEntries;
    }
    
    @Override
    public boolean isPresent(K key) {
        return containsKey(key);
    }

    @Override
    public V getByKey(K key) {
        return get(key);
    }

    @Override
    public void add(K key, V value) {
        put(key, value);
    }
}
