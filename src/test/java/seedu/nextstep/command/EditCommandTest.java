package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.storage.Storage;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class EditCommandTest {
    private InternshipList internships;
    private Storage dummyStorage;

    @BeforeEach
    void setUp() {
        internships = new InternshipList();
        internships.addInternship(new Internship("Google", "Software Engineer", 6, 2000, "P", "Java", "React"));
        internships.addInternship(new Internship("Amazon", "Data Engineer", 12, 2500, "P", "SQL", "Python"));

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

    @Test
    void testEditCommandValidEdit() throws Exception {
        // Simulate user input for valid edits:
        // First line: comma-separated fields to edit
        // Next lines: new values for each field in the same order.
        String simulatedInput = "company, role, duration, allowance, skills, status\n" +
                "NewGoogle\n" +            // company
                "Software Developer\n" +   // role
                "12\n" +                   // duration
                "3000\n" +                 // allowance
                "Java, Python\n" +         // skills
                "A\n";                     // status
        // Set System.in to use the simulated input
        System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

        // Edit the first internship ("edit 1" corresponds to index 0)
        EditCommand editCommand = new EditCommand("edit 1", internships, dummyStorage);
        editCommand.execute();

        // Verify that the internship has been updated
        Internship edited = internships.getInternship(0);
        assertEquals("NewGoogle", edited.getCompany());
        assertEquals("Software Developer", edited.getRole());
        assertEquals(12, edited.getDuration());
        assertEquals(3000, edited.getAllowance());
        // Verify skills; note getSkills() returns a List<String>
        assertArrayEquals(new String[]{"Java", "Python"}, edited.getSkills().toArray(new String[0]));
        assertEquals("A", edited.getStatus());
    }

}
