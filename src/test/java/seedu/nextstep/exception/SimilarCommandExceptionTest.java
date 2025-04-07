package seedu.nextstep.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimilarCommandExceptionTest {

    @Test
    void testSimilarCommandExceptionThrown() {
        assertThrows(SimilarCommandException.class, () -> {
            throw new SimilarCommandException();
        });
    }
}
