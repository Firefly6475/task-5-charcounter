package ua.com.foxminded.charcounter.validator; 

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ValidatorImplTest {
    private final Validator validator = new ValidatorImpl();
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfStringIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate("");
        });
        String expectedMessage = "String is empty";
        String actualMessage = exception.getMessage();
        
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfStringIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(null);
        });
        String expectedMessage = "String is null";
        String actualMessage = exception.getMessage();
        
        assertEquals(expectedMessage, actualMessage);
    }
    
    @Test
    void validateShouldNotThrowIllegalArgumentException() {
        assertDoesNotThrow(() -> validator.validate("hello"));
    }

}
