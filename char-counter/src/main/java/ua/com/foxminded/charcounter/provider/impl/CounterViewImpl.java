package ua.com.foxminded.charcounter.provider.impl;

import java.util.Map;
import ua.com.foxminded.charcounter.provider.CounterViewProvider;

public class CounterViewImpl implements CounterViewProvider {
    private static final String OUTPUT_FORM = "\"%c\" - %d%n";
    
    @Override
    public String provideView(Map<Character, Integer> charactersCount) {
        StringBuilder output = new StringBuilder();
        
        charactersCount.forEach((k, v) -> output.append(String.format(OUTPUT_FORM, k, v)));
        return output.toString();
    }
}
