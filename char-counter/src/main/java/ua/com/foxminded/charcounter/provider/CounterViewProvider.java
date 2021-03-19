package ua.com.foxminded.charcounter.provider;

import java.util.Map;

public interface CounterViewProvider {
    String provideView(Map<Character, Integer> charactersCount);
}
