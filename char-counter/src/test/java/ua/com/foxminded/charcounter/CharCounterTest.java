package ua.com.foxminded.charcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.foxminded.charcounter.provider.CounterCacheProvider;
import ua.com.foxminded.charcounter.provider.CounterUniqueCharsProvider;
import ua.com.foxminded.charcounter.provider.CounterViewProvider;
import ua.com.foxminded.charcounter.validator.Validator;

@ExtendWith(MockitoExtension.class)
public class CharCounterTest {

    @Mock
    private Validator validator;

    @Mock
    private CounterUniqueCharsProvider counterUniqueCharsProvider;

    @Mock
    private CounterViewProvider counterViewProvider;
    
    @Mock
    private CounterCacheProvider<String, Map<Character, Integer>> counterCacheProvider;

    @InjectMocks
    private CharCounter charCounter;
    
    @Test
    void countShouldReturnCachedCountedCharacters() {
        String text = "hello, my friend!";
        Map<Character, Integer> charactersCount = null;
        
        String expectedOutput = new String();
        
        doNothing().when(validator).validate(text);
        when(counterCacheProvider.isPresent(text)).thenReturn(true);
        when(counterCacheProvider.getByKey(text)).thenReturn(charactersCount);
        when(counterViewProvider.provideView(charactersCount)).thenReturn(expectedOutput);
        
        String actualOutput = charCounter.count(text);
        
        verify(counterCacheProvider).getByKey(text);
        verifyNoMoreInteractions(counterUniqueCharsProvider);
        
        assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    void countShouldReturnCountedCharacters() {
        String text = "hello, my friend!";
        Map<Character, Integer> charactersCount = null;
        
        String expectedOutput = new String();
        
        doNothing().when(validator).validate(text);
        when(counterCacheProvider.isPresent(text)).thenReturn(false);
        when(counterUniqueCharsProvider.provideUniqueChars(text)).thenReturn(charactersCount);
        doNothing().when(counterCacheProvider).add(text, charactersCount);
        when(counterViewProvider.provideView(charactersCount)).thenReturn(expectedOutput);
        
        String actualOutput = charCounter.count(text);
        
        verify(counterUniqueCharsProvider).provideUniqueChars(text);
        verify(counterCacheProvider).add(text, charactersCount);
        
        assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    void countShouldThrowIllegalArgumentExceptionIfStringIsEmpty() {
        String text = "";
        
        
        doThrow(new IllegalArgumentException("String is empty")).when(validator).validate(text);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            charCounter.count(text);
        });
        
        String expectedMessage = "String is empty";
        String actualMessage = exception.getMessage();
        
        verify(validator).validate(text);
        verifyNoMoreInteractions(counterCacheProvider);
        verifyNoMoreInteractions(counterUniqueCharsProvider);
        verifyNoMoreInteractions(counterViewProvider);
        
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void countShouldThrowIllegalArgumentExceptionIfStringIsNull() {
        String text = null;
        
        doThrow(new IllegalArgumentException("String is null")).when(validator).validate(text);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            charCounter.count(text);
        });
        
        String expectedMessage = "String is null";
        String actualMessage = exception.getMessage();
        
        verify(validator).validate(text);
        verifyNoMoreInteractions(counterCacheProvider);
        verifyNoMoreInteractions(counterUniqueCharsProvider);
        verifyNoMoreInteractions(counterViewProvider);
        
        assertEquals(expectedMessage, actualMessage);
    }
}
