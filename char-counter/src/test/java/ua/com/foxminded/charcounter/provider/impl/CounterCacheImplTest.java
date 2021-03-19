package ua.com.foxminded.charcounter.provider.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CounterCacheImplTest {
    private final CounterCacheImpl<String, Map<Character, Integer>> counterCache = new CounterCacheImpl<>(1);
    
    
    @Test
    void zeroSizeShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CounterCacheImpl<>(0));
    }
    
    @Test
    void isPresentShouldReturnTrueIfItemIsPresentInCache() {
        final String text = "hello";
        counterCache.add(text, new LinkedHashMap<>());
        
        assertTrue(counterCache.isPresent(text));
    }
    
    @Test
    void isPresentShouldReturnFalseIfItemIsNotPresentInCache() {
        final String text = "hello";
        
        assertFalse(counterCache.isPresent(text));
    }
    
    @Test
    void getByKeyShouldReturnMap() {
        final String text = "hello";
        final Map<Character, Integer> expectedMap = new LinkedHashMap<>();
        
        expectedMap.put('C', 5);
        counterCache.add(text, expectedMap);
        
        final Map<Character, Integer> actualMap = counterCache.getByKey(text);
        
        assertEquals(expectedMap, actualMap);
    }
    
    @Test
    void removeEldestEntryShouldRemoveEntryIfSizeIsGreaterThanMaxEntries() {
        final String firstText = "hello";
        final String secondText = "world";
        final Map<Character, Integer> expectedMap = new LinkedHashMap<>();
        
        expectedMap.put('C', 5);
        counterCache.add(firstText, expectedMap);
        counterCache.add(secondText, expectedMap);
        
        int expectedSize = 1;
        int actualSize = counterCache.size();
        
        assertEquals(expectedSize, actualSize);
        assertTrue(counterCache.containsKey(secondText));
        assertFalse(counterCache.containsKey(firstText));
    }
    
}
