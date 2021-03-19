package ua.com.foxminded.charcounter.provider.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.charcounter.provider.CounterUniqueCharsProvider;

public class CounterUniqueCharsImplTest {
    private final CounterUniqueCharsProvider counterUniqueChars = new CounterUniqueCharsImpl();
    
    @Test
    void provideUniqueCharsShouldReturnUniqueCharactersCountIfStringIsThreeWords() {
        String string = "hello, my friend!";
        Map<Character, Integer> expectedCharactersCount = new LinkedHashMap<>();
        
        expectedCharactersCount.put('h', 1);
        expectedCharactersCount.put('e', 2);
        expectedCharactersCount.put('l', 2);
        expectedCharactersCount.put('o', 1);
        expectedCharactersCount.put(',', 1);
        expectedCharactersCount.put(' ', 2);
        expectedCharactersCount.put('m', 1);
        expectedCharactersCount.put('y', 1);
        expectedCharactersCount.put('f', 1);
        expectedCharactersCount.put('r', 1);
        expectedCharactersCount.put('i', 1);
        expectedCharactersCount.put('n', 1);
        expectedCharactersCount.put('d', 1);
        expectedCharactersCount.put('!', 1);
        
        Map <Character, Integer> actualCharactersCount = counterUniqueChars.provideUniqueChars(string);
        
        assertEquals(expectedCharactersCount, actualCharactersCount);
    }
    
    @Test
    void provideUniqueCharsShouldReturnUniqueCharactersCountIfStringContainsMultipleEqualsSymbols() {
        String string = "hhhhhheeeeeeee";
        Map<Character, Integer> expectedCharactersCount = new LinkedHashMap<>();
        
        expectedCharactersCount.put('h', 6);
        expectedCharactersCount.put('e', 8);
        
        Map <Character, Integer> actualCharactersCount = counterUniqueChars.provideUniqueChars(string);
        
        assertEquals(expectedCharactersCount, actualCharactersCount);
    }
    
    @Test
    void provideUniqueCharsShouldReturnUniqueCharactersCountIfStringContainsOnlyWhitespaces() {
        String string = "     ";
        Map<Character, Integer> expectedCharactersCount = new LinkedHashMap<>();
        
        expectedCharactersCount.put(' ', 5);
        
        Map <Character, Integer> actualCharactersCount = counterUniqueChars.provideUniqueChars(string);
        
        assertEquals(expectedCharactersCount, actualCharactersCount);
    }
}
