package seedu.nextstep.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.command.*;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.*;
import seedu.nextstep.storage.Storage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        InternshipList internships = new InternshipList();
        Storage dummyStorage = new Storage() {
            @Override
            public void save(InternshipList internships) {
            }
        };
        parser = new Parser(internships, dummyStorage);
    }

    @Test
    void testSimilarCommand() {
        assertThrows(SimilarCommandException.class, () -> parser.createCommand("filter", "filter"));
    }

    @Test
    void testEmptyInput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        parser.processCommand("");

        String output = outContent.toString().trim();
        assertTrue(output.contains("Error: No command entered."));
        assertTrue(output.contains("____________________________________"));

        System.setOut(System.out);
    }

    @Test
    void testUnknownCommand() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        parser.processCommand("invalid command");

        String output = outContent.toString().trim();
        assertTrue(output.contains("Unknown command"));
        assertTrue(output.contains("____________________________________"));

        System.setOut(System.out);
    }

    @Test
    void testCreateCommand_validCommands() throws SimilarCommandException {
        assertInstanceOf(AddCommand.class, parser.createCommand("add", "add"));
        assertInstanceOf(DeleteCommand.class, parser.createCommand("delete", "delete"));
        assertInstanceOf(EditCommand.class, parser.createCommand("edit", "edit"));
        assertInstanceOf(ListCommand.class, parser.createCommand("list", "list"));
        assertInstanceOf(HelpCommand.class, parser.createCommand("help", "help"));
        assertInstanceOf(FindSkillCommand.class, parser.createCommand("find/s", "find/s"));
        assertInstanceOf(FindRoleCommand.class, parser.createCommand("find/r", "find/r"));
        assertInstanceOf(FindCompanyCommand.class, parser.createCommand("find/c", "find/c"));
        assertInstanceOf(FilterCommand.class, parser.createCommand("filter/a", "filter/a"));
        assertInstanceOf(FilterCommand.class, parser.createCommand("filter/d", "filter/d"));
    }

    @Test
    void testNumberFormatExceptionHandling() {
        String[] commandsToTest = {"delete", "edit"};

        for (String cmd : commandsToTest) {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            parser.processCommand(cmd + " invalid_number");

            String output = outContent.toString().trim();
            assertTrue(output.contains("Error:"));
            assertTrue(output.contains("must be an integer"));

            System.setOut(System.out);
        }
    }
}
