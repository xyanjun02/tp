package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private InternshipList internships;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        internships = new InternshipList();
    }

    @Test
    void testListCommandEmptyList() {
        ListCommand listCommand = new ListCommand(internships);
        try {
            listCommand.execute();
        } catch (Exception e) {
            fail("ListCommand threw an unexpected exception: " + e.getMessage());
        }
        String output = outContent.toString();
        assertTrue(output.contains("No internships here (0-0). Try adding some."));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    void testListCommandWithInternships() {
        internships.addInternship(new Internship("Google", "SWE", 6, 2000, "Java"));
        internships.addInternship(new Internship("Amazon", "PM", 3, 1500, "Leadership"));

        ListCommand listCommand = new ListCommand(internships);
        try {
            listCommand.execute();
        } catch (Exception e) {
            fail("ListCommand threw an unexpected exception: " + e.getMessage());
        }

        String output = outContent.toString();
        assertTrue(output.contains("Company"));
        assertTrue(output.contains("Role"));
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("Amazon"));
    }

    @Test
    void testListCommandNoSideEffects() {
        internships.addInternship(new Internship("Meta", "DS", 12, 3000, "Python"));

        ListCommand listCommand = new ListCommand(internships);
        try {
            listCommand.execute();
        } catch (Exception e) {
            fail("ListCommand threw an unexpected exception: " + e.getMessage());
        }

        assertEquals(1, internships.size());
    }
}
