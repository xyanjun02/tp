package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.InvalidIntegerException;
import seedu.nextstep.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class AddCommandTest {
    private InternshipList internships;
    private Storage dummyStorage;

    @BeforeEach
    void setUp() {
        internships = new InternshipList();
        // Dummy Storage that overrides save() to do nothing.
        dummyStorage = new Storage() {
            @Override
            public void save(InternshipList internships) {
                // no-op for testing
            }
        };
    }

    @Test
    void testAddCommandValidInput() {
        String input = "add c/Google r/Software Engineer d/6 a/2000 s/Java, React, C++, st/A";
        AddCommand addCommand = new AddCommand(input, internships, dummyStorage);
        try {
            addCommand.execute();
        } catch (InvalidInputFormatException | EmptyInputException | InvalidIntegerException e) {
            fail(e.getMessage());
        }
        assertEquals(1, internships.size());
        try {
            Internship addedInternship = internships.getInternship(0);
            assertEquals("Google", addedInternship.getCompany());
            assertEquals("Software Engineer", addedInternship.getRole());
            assertEquals(6, addedInternship.getDuration());
            assertEquals(2000, addedInternship.getAllowance());
            assertArrayEquals(new String[]{"Java", "React", "C++"}, addedInternship.getSkills().toArray());
        } catch (InvalidIndexException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testAddCommandMissingFields() {
        String input = "add r/Computer Engineer d/12 a/1500 s/Verilog, C, st/A";
        AddCommand addCommand = new AddCommand(input, internships, dummyStorage);
        assertThrows(InvalidInputFormatException.class, addCommand::execute);
        assertEquals(0, internships.size());
    }

    @Test
    void testAddCommandEmptyFields() {
        String input = "add c/Apple r/ d/6 a/5000 s/C, Python, st/-";
        AddCommand addCommand = new AddCommand(input, internships, dummyStorage);
        assertThrows(InvalidInputFormatException.class, addCommand::execute);
        assertEquals(0, internships.size());
    }

    @Test
    void testAddCommandEmptyInput() {
        String input = "add";
        AddCommand addCommand = new AddCommand(input, internships, dummyStorage);
        assertThrows(EmptyInputException.class, addCommand::execute);
        assertEquals(0, internships.size());
    }

    @Test
    void testAddCommandNonIntegerInput() {
        String input = "add c/Microsoft r/Data Analyst d/Six a/2000 s/SQL, Python, st/A";
        AddCommand addCommand = new AddCommand(input, internships, dummyStorage);
        assertThrows(NumberFormatException.class, addCommand::execute);
        assertEquals(0, internships.size());
    }

    @Test
    void testAddCommandNegativeAllowance() {
        String input = "add c/Amazon r/Project Manager d/12 a/-3000 s/Excel, st/-";
        AddCommand addCommand = new AddCommand(input, internships, dummyStorage);
        assertThrows(InvalidIntegerException.class, addCommand::execute);
        assertEquals(0, internships.size());
    }

    @Test
    void testAddCommandInvalidStatus() {
        String input = "add c/Amazon r/Project Manager d/12 a/3000 s/Excel, st/Accepted";
        AddCommand addComand = new AddCommand(input, internships, dummyStorage);
        assertThrows(InvalidInputFormatException.class, addComand::execute);
        assertEquals(0, internships.size());
    }

    @Test
    void testAddCommandUnrecognizedFlag() {
        String input = "add c/Amazon r/Project Manager d/12 a/3000 xyz/Unknown s/Excel st/A";
        AddCommand addCommand = new AddCommand(input, internships, dummyStorage);
        assertThrows(InvalidInputFormatException.class, addCommand::execute);
        assertEquals(0, internships.size());
    }
}


