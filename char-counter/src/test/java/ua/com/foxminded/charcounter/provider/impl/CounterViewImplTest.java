package ua.com.foxminded.charcounter.provider.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.charcounter.provider.CounterViewProvider;

public class CounterViewImplTest {
    private final CounterViewProvider counterView = new CounterViewImpl();
    
    @Test
    void provideViewShouldReturnViewIfStringIsThreeWords() {
        Map<Character, Integer> charactersCount = new LinkedHashMap<>();
        
        charactersCount.put('h', 1);
        charactersCount.put('e', 2);
        charactersCount.put('l', 2);
        charactersCount.put('o', 1);
        charactersCount.put(',', 1);
        charactersCount.put(' ', 2);
        charactersCount.put('m', 1);
        charactersCount.put('y', 1);
        charactersCount.put('f', 1);
        charactersCount.put('r', 1);
        charactersCount.put('i', 1);
        charactersCount.put('n', 1);
        charactersCount.put('d', 1);
        charactersCount.put('!', 1);
        
        String expectedView = "\"h\" - 1\r\n"
                            + "\"e\" - 2\r\n"
                            + "\"l\" - 2\r\n"
                            + "\"o\" - 1\r\n"
                            + "\",\" - 1\r\n"
                            + "\" \" - 2\r\n"
                            + "\"m\" - 1\r\n"
                            + "\"y\" - 1\r\n"
                            + "\"f\" - 1\r\n"
                            + "\"r\" - 1\r\n"
                            + "\"i\" - 1\r\n"
                            + "\"n\" - 1\r\n"
                            + "\"d\" - 1\r\n"
                            + "\"!\" - 1\r\n";
        
        String actualView = counterView.provideView(charactersCount);
        
        assertEquals(expectedView, actualView);
    }
    
    @Test
    void provideViewShouldReturnViewIfStringContainsMultipleEqualsSymbols() {
        Map<Character, Integer> charactersCount = new LinkedHashMap<>();
        
        charactersCount.put('h', 6);
        charactersCount.put('e', 8);
        
        String expectedView = "\"h\" - 6\r\n"
                            + "\"e\" - 8\r\n";
        
        String actualView = counterView.provideView(charactersCount);
        
        assertEquals(expectedView, actualView);
    }
}
