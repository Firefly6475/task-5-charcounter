package ua.com.foxminded.charcounter.provider;

import java.util.Map;

public interface CounterUniqueCharsProvider {
    Map<Character, Integer> provideUniqueChars(String string);
}
