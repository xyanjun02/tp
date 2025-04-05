package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class HelpCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testHelpCommandOutputContainsKeyCommands() {
        HelpCommand helpCommand = new HelpCommand();
        try {
            helpCommand.execute();
        } catch (Exception e) {
            fail("HelpCommand threw an unexpected exception: " + e.getMessage());
        }

        String output = outContent.toString();
        assertTrue(output.contains("Do help yourself to these commands! (0u0)"));
        assertTrue(output.contains("1. add [c/COMPANY] [r/ROLE]"));
        assertTrue(output.contains("10. bye - Exits the application"));
    }

    @Test
    void testHelpCommandNoExceptionsThrown() {
        HelpCommand helpCommand = new HelpCommand();
        try {
            helpCommand.execute();
        } catch (Exception e) {
            fail("HelpCommand should not throw exceptions");
        }
    }
}
