package seedu.nextstep.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindCommandTest {

    private InternshipList internships;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        // Create a new internship list and preload sample data.
        internships = new InternshipList();
        internships.addInternship(new Internship("Google", "Software Engineer", 6, 2000, "P", "Java", "Python"));
        internships.addInternship(new Internship("Amazon", "Data Engineer", 12, 2500, "P", "SQL", "Scala"));
        internships.addInternship(new Internship("Facebook", "Product Manager", 3, 1500, "-", "Communication", "Management")
        );

        // Redirect System.out to capture output for verification.
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Restore the original System.out after each test.
        System.setOut(originalOut);
    }

    @Test
    void testFindSkillCommandValid() throws EmptyInputException {
        // Test searching for a skill ("Java") that exists in one internship.
        String input = "find/s Java";
        FindSkillCommand findSkillCommand = new FindSkillCommand(input, internships);
        findSkillCommand.execute();

        String output = outContent.toString();
        // Verify that the output contains the "Searching" message and details of the matching internship.
        assertTrue(output.contains("Searching for internships with skill: Java"));
        assertTrue(output.contains("Google")); // "Google" internship contains "Java"
    }

    @Test
    void testFindSkillCommandNoMatch() throws EmptyInputException {
        // Test searching for a skill ("Ruby") that does not exist.
        String input = "find/s Ruby";
        FindSkillCommand findSkillCommand = new FindSkillCommand(input, internships);
        findSkillCommand.execute();

        String output = outContent.toString();
        // Verify that the output shows a "No internships found" message.
        assertTrue(output.contains("No internships found with skill: Ruby"));
    }

    @Test
    void testFindCompanyCommandValid() throws EmptyInputException {
        // Test searching for a company ("Amazon") that exists.
        String input = "find/c Amazon";
        FindCompanyCommand findCompanyCommand = new FindCompanyCommand(input, internships);
        findCompanyCommand.execute();

        String output = outContent.toString();
        // Verify that the output contains the searching message and details for "Amazon".
        assertTrue(output.contains("Searching for internships with company: Amazon"));
        assertTrue(output.contains("Amazon"));
    }

    @Test
    void testFindCompanyCommandNoMatch() throws EmptyInputException {
        // Test searching for a company ("Netflix") that doesn't exist.
        String input = "find/c Netflix";
        FindCompanyCommand findCompanyCommand = new FindCompanyCommand(input, internships);
        findCompanyCommand.execute();

        String output = outContent.toString();
        // Verify that the output shows a "No internships found" message.
        assertTrue(output.contains("No internships found with company: Netflix"));
    }

    @Test
    void testFindRoleCommandValid() throws EmptyInputException {
        // Test searching for a role ("Data Engineer") that exists.
        String input = "find/r Data Engineer";
        FindRoleCommand findRoleCommand = new FindRoleCommand(input, internships);
        findRoleCommand.execute();

        String output = outContent.toString();
        // Verify that the output contains the searching message and details for "Data Engineer".
        assertTrue(output.contains("Searching for internships with role: Data Engineer"));
        assertTrue(output.contains("Data Engineer"));
    }

    @Test
    void testFindRoleCommandMultipleRoles() throws EmptyInputException {
        // Test searching for multiple roles ("Product Manager, Software Engineer").
        String input = "find/r Product Manager, Software Engineer";
        FindRoleCommand findRoleCommand = new FindRoleCommand(input, internships);
        findRoleCommand.execute();

        String output = outContent.toString();
        // Verify that the output contains details for both roles.
        assertTrue(output.contains("Searching for internships with role: Product Manager, Software Engineer"));
        assertTrue(output.contains("Facebook")); // Matches Product Manager
        assertTrue(output.contains("Google"));   // Matches Software Engineer
    }

    @Test
    void testFindRoleCommandNoMatch() throws EmptyInputException {
        // Test searching for a role ("Intern") that does not exist.
        String input = "find/r Intern";
        FindRoleCommand findRoleCommand = new FindRoleCommand(input, internships);
        findRoleCommand.execute();

        String output = outContent.toString();
        // Verify that the output shows a "No internships found" message.
        assertTrue(output.contains("No internships found with role: Intern"));
    }
}
