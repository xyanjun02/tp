package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.InvalidIntegerException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class FilterCommandTest {
    private InternshipList internships;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        internships = new InternshipList();
        internships.addInternship(new Internship("Google", "Software Engineer", 3, 5000, "Java", "Python"));
        internships.addInternship(new Internship("Apple", "iOS Developer", 6, 4000, "Swift", "Objective-C"));
        internships.addInternship(new Internship("Amazon", "Data Scientist", 4, 3000, "Python", "R"));
        internships.addInternship(new Internship("Microsoft", "Product Manager", 3, 6000, "Excel", "PowerPoint"));
        internships.addInternship(new Internship("Netflix", "UI/UX Designer", 2, 2500, "Figma", "Sketch"));

        // Redirect System.out to capture output for testing
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    void tearDown() {
        // Restore original System.out after tests
        System.setOut(originalOut);
    }

    @Test
    void testFilterCommandValidAllowanceMin() {
        String input = "filter/a 4000";
        FilterCommand filterCommand = new FilterCommand(input, internships);

        try {
            filterCommand.execute();
        } catch (InvalidInputFormatException | EmptyInputException | InvalidIntegerException e) {
            fail(e.getMessage());
        }
        String output = outContent.toString();
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("Apple"));
        assertTrue(output.contains("Microsoft"));
        assertFalse(output.contains("Amazon"));
        assertFalse(output.contains("Netflix"));
    }

    @Test
    void testFilterCommandValidAllowanceRange() {
        String input = "filter/a 3000 5000";
        FilterCommand filterCommand = new FilterCommand(input, internships);

        try {
            filterCommand.execute();
        } catch (InvalidInputFormatException | EmptyInputException | InvalidIntegerException e) {
            fail(e.getMessage());
        }
        String output = outContent.toString();
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("Apple"));
        assertTrue(output.contains("Amazon"));
        assertFalse(output.contains("Microsoft"));
        assertFalse(output.contains("Netflix"));
    }

    @Test
    void testFilterCommandValidNoMatchingInternships() {
        String input = "filter/a 7000";
        FilterCommand filterCommand = new FilterCommand(input, internships);

        try {
            filterCommand.execute();
        } catch (InvalidInputFormatException | EmptyInputException | InvalidIntegerException e) {
            fail(e.getMessage());
        }
        String output = outContent.toString();
        assertTrue(output.contains("No internships found here!"));
    }

    @Test
    void testFilterCommandEmptyAllowance() {
        String input = "filter/a";
        FilterCommand filterCommand = new FilterCommand(input, internships);
        assertThrows(EmptyInputException.class, filterCommand::execute);
    }

    @Test
    void testFilterCommandEmptyDuration() {
        String input = "filter/d";
        FilterCommand filterCommand = new FilterCommand(input, internships);
        assertThrows(EmptyInputException.class, filterCommand::execute);
    }

    @Test
    void testFilterCommandTooManyValues() {
        String input = "filter/a 1000 2000 3000";
        FilterCommand filterCommand = new FilterCommand(input, internships);
        assertThrows(InvalidInputFormatException.class, filterCommand::execute);
    }

    @Test
    void testFilterCommandNonInteger() {
        String input = "filter/a thousand";
        FilterCommand filterCommand = new FilterCommand(input, internships);
        assertThrows(NumberFormatException.class, filterCommand::execute);
    }

    @Test
    void testFilterCommandMinHigherThanMax() {
        String input = "filter/a 3000 2000";
        FilterCommand filterCommand = new FilterCommand(input, internships);
        assertThrows(InvalidInputFormatException.class, filterCommand::execute);
    }

    @Test
    void testFilterCommandNegativeValues() {
        String input = "filter/d -5";
        FilterCommand filterCommand = new FilterCommand(input, internships);
        assertThrows(InvalidIntegerException.class, filterCommand::execute);
    }
}
