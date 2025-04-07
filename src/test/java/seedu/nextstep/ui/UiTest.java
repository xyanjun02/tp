package seedu.nextstep.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.core.Internship;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Internship dummyInternship;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        dummyInternship = new Internship("Google", "SWE", 3, 2000, "Java");
    }

    @Test
    public void testPrintLinebreak() {
        Ui.printLinebreak();
        assertEquals("____________________________________", outContent.toString().trim());
    }

    @Test
    public void testPrintWelcomeMessage() {
        Ui.printWelcomeMessage();
        String output = outContent.toString();
        assertTrue(output.contains("Welcome to NextStep"));
        assertTrue(output.contains("What would you like to do today"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintEnterCommand() {
        Ui.printEnterCommand();
        assertEquals("Enter command: ", outContent.toString());
    }

    @Test
    public void testPrintUnknownCommand() {
        Ui.printUnknownCommand();
        String output = outContent.toString();
        assertTrue(output.contains("Unknown command"));
        assertTrue(output.contains("Type help for more info"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintExitMessage() {
        Ui.printExitMessage();
        String output = outContent.toString();
        assertTrue(output.contains("Thank you for using NextStep"));
        assertTrue(output.contains("good luck on your search"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testShowError() {
        String errorMessage = "Test error message";
        Ui.showError(errorMessage);
        String output = outContent.toString();
        assertTrue(output.contains(errorMessage));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintSimilarCommandError_filter() {
        Ui.printSimilarCommandError("filter");
        String output = outContent.toString();
        assertTrue(output.contains("No command found. Perhaps you mean filter/a or filter/d?"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintSimilarCommandError_find() {
        Ui.printSimilarCommandError("find");
        String output = outContent.toString();
        assertTrue(output.contains("No command found. Perhaps you mean find/c, find/r, or find/s?"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintAddingMessage() {
        Ui.printAddingMessage(dummyInternship);
        String output = outContent.toString();
        assertTrue(output.contains("New internship added below!"));
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("SWE"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintDeleteSuccess() {
        Ui.printDeleteSuccess(dummyInternship);
        String output = outContent.toString();
        assertTrue(output.contains("The Following Internship is Deleted:"));
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("SWE"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintEditMessage() {
        Ui.printEditMessage();
        String output = outContent.toString();
        assertTrue(output.contains("What would you like to edit?"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintEditSuccess() {
        Ui.printEditSuccess(dummyInternship);
        String output = outContent.toString();
        assertTrue(output.contains("Successfully edited the following Internship:"));
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("SWE"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintSearchingForSkill() {
        String skill = "Java";
        Ui.printSearchingForSkill(skill);
        String output = outContent.toString();
        assertTrue(output.contains("Searching for internships with skill: " + skill));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintNoInternshipFound() {
        String skill = "Python";
        Ui.printNoInternshipFound(skill);
        String output = outContent.toString();
        assertTrue(output.contains("No internships found with skill: " + skill));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintInternship() {
        Ui.printInternship(dummyInternship);
        String output = outContent.toString();
        assertTrue(output.contains("Google"));
        assertTrue(output.contains("SWE"));
        assertTrue(output.contains("Java"));
    }

    @Test
    public void testPrintSearchingForRole() {
        String role = "SWE";
        Ui.printSearchingForRole(role);
        String output = outContent.toString();
        assertTrue(output.contains("Searching for internships with role: " + role));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintNoInternshipFoundForRole() {
        String role = "Data Scientist";
        Ui.printNoInternshipFoundForRole(role);
        String output = outContent.toString();
        assertTrue(output.contains("No internships found with role: " + role));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintSearchingForCompany() {
        String company = "Google";
        Ui.printSearchingForCompany(company);
        String output = outContent.toString();
        assertTrue(output.contains("Searching for internships with company: " + company));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintNoInternshipFoundForCompany() {
        String company = "Facebook";
        Ui.printNoInternshipFoundForCompany(company);
        String output = outContent.toString();
        assertTrue(output.contains("No internships found with company: " + company));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintNoFilteredInternshipFound() {
        Ui.printNoFilteredInternshipFound();
        String output = outContent.toString();
        assertTrue(output.contains("No internships found here!"));
        assertTrue(output.contains("____________________________________"));
    }

    @Test
    public void testPrintMessage() {
        String message = "Test message";
        Ui.printMessage(message);
        assertEquals(message + System.lineSeparator(), outContent.toString());
    }
}
