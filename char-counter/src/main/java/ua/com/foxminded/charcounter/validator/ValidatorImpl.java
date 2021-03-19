package ua.com.foxminded.charcounter.validator;

public class ValidatorImpl implements Validator {
    @Override
    public void validate (String string) {
        if (string == null) {
            throw new IllegalArgumentException("String is null");
        }
        if (string.isEmpty()) {
            throw new IllegalArgumentException("String is empty");
        }
    }
}
