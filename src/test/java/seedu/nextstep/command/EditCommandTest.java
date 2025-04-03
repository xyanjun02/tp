package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.storage.Storage;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EditCommandTest {
    private InternshipList internships;
    private Storage dummyStorage;

    @BeforeEach
    void setUp() {
        internships = new InternshipList();
        internships.addInternship(new Internship("Google", "Software Engineer", 6, 2000, "Java, React"));
        internships.addInternship(new Internship("Amazon", "Data Engineer", 12, 2500, "SQL, Python"));

        dummyStorage = new Storage() {
            @Override
            public void save(InternshipList internships) {
            }
        };
    }

    @Test
    void testEditCommandInvalidIndex() {
        EditCommand editCommand = new EditCommand("edit 5", internships, dummyStorage);
        assertThrows(InvalidIndexException.class, editCommand::execute);
    }

    @Test
    void testEditCommandEmptyFields() {
        EditCommand editCommand = new EditCommand("edit 1", internships, dummyStorage);
        System.setIn(new java.io.ByteArrayInputStream("\n".getBytes()));
        assertThrows(NoSuchElementException.class, editCommand::execute);
    }
}