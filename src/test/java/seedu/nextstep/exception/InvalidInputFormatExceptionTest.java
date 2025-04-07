package seedu.nextstep.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidInputFormatExceptionTest {

    @Test
    void testInvalidInputFormatExceptionMessage() {
        String expectedMessage = "Invalid input format";
        InvalidInputFormatException exception = new InvalidInputFormatException(expectedMessage);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testInvalidInputFormatExceptionThrown() {
        String expectedMessage = "Invalid input format";

        InvalidInputFormatException exception = assertThrows(InvalidInputFormatException.class, () -> {
            throw new InvalidInputFormatException(expectedMessage);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}
