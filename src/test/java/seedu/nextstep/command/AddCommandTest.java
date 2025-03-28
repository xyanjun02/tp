package seedu.nextstep.command;

import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
import seedu.nextstep.NextStep;
// import seedu.nextstep.core.Internship;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCommandTest {

    @BeforeEach
    void setUp() {
        // Clear the internship list before each test
        NextStep.internships.clear();
    }

//    @Test
//    void testAddCommandValidInput() {
//        // Use comma-separated skills since AddCommand splits by commas.
//        String input = "add c/Google r/Software Engineer d/6 a/2000 s/Java, React, C++";
//        AddCommand addCommand = new AddCommand(input);
//        addCommand.execute();
//
//        assertEquals(1, NextStep.internships.size());
//        Internship addedInternship = NextStep.internships.get(0);
//        assertEquals("Google", addedInternship.getCompany());
//        assertEquals("Software Engineer", addedInternship.getRole());
//        assertEquals(6, addedInternship.getDuration());
//        assertEquals(2000, addedInternship.getAllowance());
//        assertArrayEquals(new String[]{"Java", "React", "C++"}, addedInternship.getSkills().toArray());
    //    }
//
//    @Test
//    void testAddCommandMissingField() {
//        // Missing the company field ("c/" is absent)
//        String input = "add r/Software Engineer d/12 a/5000 s/Java, Python, C++";
//        AddCommand addCommand = new AddCommand(input);
//        addCommand.execute();
//        assertTrue(NextStep.internships.isEmpty());
    //      }
}


