package seedu.nextstep.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidIndexExceptionTest {

    @Test
    void testInvalidIndexException() {
        InvalidIndexException exception = assertThrows(InvalidIndexException.class, () -> {
            throw new InvalidIndexException("Test message");
        });

        assert(exception.getMessage().equals("Test message"));
    }
}
