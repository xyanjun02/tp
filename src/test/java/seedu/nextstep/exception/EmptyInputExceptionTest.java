package seedu.nextstep.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EmptyInputExceptionTest {

    @Test
    void testEmptyInputExceptionMessage() {
        EmptyInputException exception = assertThrows(EmptyInputException.class, () -> {
            throw new EmptyInputException("Test message");
        });

        assert(exception.getMessage().equals("Test message"));
    }
}
