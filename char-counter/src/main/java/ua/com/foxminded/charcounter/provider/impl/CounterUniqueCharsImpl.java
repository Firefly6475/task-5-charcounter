package ua.com.foxminded.charcounter.provider.impl;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import ua.com.foxminded.charcounter.provider.CounterUniqueCharsProvider;

public class CounterUniqueCharsImpl implements CounterUniqueCharsProvider {
    @Override
    public Map<Character, Integer> provideUniqueChars(String text) {
        return text.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, e -> 1, Integer::sum)));
    }
}
