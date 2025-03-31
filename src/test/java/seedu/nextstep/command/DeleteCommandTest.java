package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
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

        // Preload two internships for deletion testing.
        internships.addInternship(new Internship("Google", "Software Engineer", 6, 2000, "Java", "Python"));
        internships.addInternship(new Internship("Amazon", "Data Engineer", 12, 2500, "SQL", "Scala"));
    }

    @Test
    void testDeleteCommandValidInput() {
        DeleteCommand deleteCommand = new DeleteCommand("delete 1", internships, dummyStorage);
        try {
            deleteCommand.execute();
        } catch (EmptyInputException | InvalidInputFormatException | InvalidIndexException e) {
            org.junit.jupiter.api.Assertions.fail("No exception should be thrown for valid deletion: " + e.getMessage());
        }
        assertEquals(1, internships.size());
        Internship remaining = internships.getAllInternships().get(0);
        assertEquals("Amazon", remaining.getCompany());
    }

    @Test
    void testDeleteCommandMissingIndex() {
        DeleteCommand deleteCommand = new DeleteCommand("delete", internships, dummyStorage);
        try {
            deleteCommand.execute();
            org.junit.jupiter.api.Assertions.fail("Expected an exception for missing index.");
        } catch (EmptyInputException | InvalidInputFormatException | InvalidIndexException e) {
            // Expected exception; do nothing.
        }
        assertEquals(2, internships.size());
    }

    @Test
    void testDeleteCommandInvalidIndex() {
        DeleteCommand deleteCommand = new DeleteCommand("delete 3", internships, dummyStorage);
        try {
            deleteCommand.execute();
            org.junit.jupiter.api.Assertions.fail("Expected an exception for invalid index.");
        } catch (EmptyInputException | InvalidInputFormatException | InvalidIndexException e) {
            // Expected exception; do nothing.
        }
        assertEquals(2, internships.size());
    }
}

