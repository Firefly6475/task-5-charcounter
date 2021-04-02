package ua.com.foxminded.charcounter.provider;

public interface CounterCacheProvider<K, V> {
    
    boolean isPresent(K key);

    V getByKey(K key);

    void add(K key, V value);

}
