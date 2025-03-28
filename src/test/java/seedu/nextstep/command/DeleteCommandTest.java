package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
//  import org.junit.jupiter.api.Test;
import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;

//  import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    @BeforeEach
    void setUp() {
        // Clear the internship list and add sample internships for testing.
        NextStep.internships.clear();
        NextStep.internships.add(new Internship("Google", "Software Engineer",
                6, 2000, new String[]{"Java", "Python"}));
        NextStep.internships.add(new Internship("Amazon", "Data Engineer",
                12, 2500, new String[]{"SQL", "Scala"}));
    }

//    @Test
//    void testDeleteCommandValidInput() {
//        // Deleting the first internship using a valid index ("delete 1").
//        DeleteCommand deleteCommand = new DeleteCommand("delete 1");
//        deleteCommand.execute();
//
//        // After deletion, only one internship should remain.
//        assertEquals(1, NextStep.internships.size());
//        // Verify that the remaining internship is the second one originally added.
//        assertEquals("Amazon", NextStep.internships.get(0).getCompany());
//    }
//
//    @Test
//    void testDeleteCommandMissingIndex() {
//        // Test deleting without providing an index ("delete")
//        DeleteCommand deleteCommand = new DeleteCommand("delete");
//        deleteCommand.execute();
//        // Since error is handled internally, no exception is thrown,
//        // but we can verify that the internships list remains unchanged.
//        assertEquals(2, NextStep.internships.size());
//    }
//
//    @Test
//    void testDeleteCommandInvalidIndex() {
//        // Test deleting with an out-of-range index ("delete 3" when only 2 internships exist)
//        DeleteCommand deleteCommand = new DeleteCommand("delete 3");
//        deleteCommand.execute();
//        // Ensure that no internship is removed.
//        assertEquals(2, NextStep.internships.size());
//      }
}

