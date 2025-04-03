package seedu.nextstep.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternshipTest {

    private Internship internship;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        internship = new Internship("Google", "Software Engineer", 6, 2000, "Java", "Python");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testGetCompany() {
        assertEquals("Google", internship.getCompany());
    }

    @Test
    void testSetCompany() {
        internship.setCompany("Amazon");
        assertEquals("Amazon", internship.getCompany());
    }

    @Test
    void testGetRole() {
        assertEquals("Software Engineer", internship.getRole());
    }

    @Test
    void testSetRole() {
        internship.setRole("Data Scientist");
        assertEquals("Data Scientist", internship.getRole());
    }

    @Test
    void testGetDuration() {
        assertEquals(6, internship.getDuration());
    }

    @Test
    void testSetDuration() {
        internship.setDuration(12);
        assertEquals(12, internship.getDuration());
    }

    @Test
    void testGetAllowance() {
        assertEquals(2000, internship.getAllowance());
    }

    @Test
    void testSetAllowance() {
        internship.setAllowance(2500);
        assertEquals(2500, internship.getAllowance());
    }

    @Test
    void testGetSkills() {
        List<String> skills = internship.getSkills();
        assertTrue(skills.contains("Java"));
        assertTrue(skills.contains("Python"));
    }

    @Test
    void testSetSkills() {
        internship.setSkills("C++", "Ruby");
        List<String> skills = internship.getSkills();
        assertTrue(skills.contains("C++"));
        assertTrue(skills.contains("Ruby"));
    }

    @Test
    void testGetStatus() {
        assertEquals("-", internship.getStatus());
    }

    @Test
    void testSetStatus() {
        internship.setStatus("Pending");
        assertEquals("Pending", internship.getStatus());
    }

    @Test
    void testToString() {
        String output = internship.toString();
        assertTrue(output.contains("Company   : Google"));
        assertTrue(output.contains("Role      : Software Engineer"));
        assertTrue(output.contains("Duration  : 6 months"));
        assertTrue(output.contains("Allowance : $2000"));
        assertTrue(output.contains("Skills    : Java, Python"));
        assertTrue(output.contains("Status    : -"));
    }
}
