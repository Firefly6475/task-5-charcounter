package ua.com.foxminded.charcounter;

import java.util.Map;
import ua.com.foxminded.charcounter.provider.CounterCacheProvider;
import ua.com.foxminded.charcounter.provider.CounterUniqueCharsProvider;
import ua.com.foxminded.charcounter.provider.CounterViewProvider;
import ua.com.foxminded.charcounter.validator.Validator;

public class CharCounter {
    private final Validator validator;
    private final CounterUniqueCharsProvider uniqueCharsProvider;
    private final CounterViewProvider viewProvider;
    private final CounterCacheProvider<String, Map<Character, Integer>> cacheProvider;

    public CharCounter(Validator validator, CounterUniqueCharsProvider uniqueCharsProvider,
            CounterViewProvider viewProvider,
            CounterCacheProvider<String, Map<Character, Integer>> cacheProvider) {
        this.validator = validator;
        this.uniqueCharsProvider = uniqueCharsProvider;
        this.viewProvider = viewProvider;
        this.cacheProvider = cacheProvider;
    }

    public String count(String text) {
        validator.validate(text);
        
        final Map<Character, Integer> symbolToCount;
        if (cacheProvider.isPresent(text)) {
            symbolToCount = cacheProvider.getByKey(text);
        } else {
            symbolToCount = uniqueCharsProvider.provideUniqueChars(text);
            cacheProvider.add(text, symbolToCount);
        }

        return viewProvider.provideView(symbolToCount);
    }
}
