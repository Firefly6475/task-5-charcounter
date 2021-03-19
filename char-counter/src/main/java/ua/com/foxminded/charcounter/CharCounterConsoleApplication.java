package ua.com.foxminded.charcounter;

import java.util.Map;
import java.util.Scanner;
import ua.com.foxminded.charcounter.provider.CounterCacheProvider;
import ua.com.foxminded.charcounter.provider.CounterUniqueCharsProvider;
import ua.com.foxminded.charcounter.provider.CounterViewProvider;
import ua.com.foxminded.charcounter.provider.impl.CounterCacheImpl;
import ua.com.foxminded.charcounter.provider.impl.CounterUniqueCharsImpl;
import ua.com.foxminded.charcounter.provider.impl.CounterViewImpl;
import ua.com.foxminded.charcounter.validator.Validator;
import ua.com.foxminded.charcounter.validator.ValidatorImpl;

public class CharCounterConsoleApplication {

    public static void main(String[] args) {
        Validator validator = new ValidatorImpl();
        CounterUniqueCharsProvider uniqueCharsProvider = new CounterUniqueCharsImpl();
        CounterViewProvider viewProvider = new CounterViewImpl();
        CounterCacheProvider<String, Map<Character, Integer>> cacheProvider = new CounterCacheImpl<>(100);
        CharCounter charCounter =
                new CharCounter(validator, uniqueCharsProvider, viewProvider, cacheProvider);
        try (Scanner in = new Scanner(System.in)) {
            String string = "";
            System.out.println("Enter string");
            string = in.nextLine();
            System.out.println(charCounter.count(string));
        }
    }

}
