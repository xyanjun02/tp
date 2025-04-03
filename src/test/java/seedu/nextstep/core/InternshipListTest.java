package seedu.nextstep.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nextstep.exception.InvalidIndexException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InternshipListTest {

    private InternshipList internshipList;

    @BeforeEach
    void setUp() {
        internshipList = new InternshipList();
        internshipList.addInternship(new Internship("Google", "Software Engineer", 6, 2000, "Java", "Python"));
        internshipList.addInternship(new Internship("Amazon", "Data Engineer", 12, 2500, "SQL", "Scala"));
    }

    @Test
    void testAddInternship() {
        Internship internship = new Internship("Facebook", "Product Manager", 3, 1500, "Communication", "Management");
        internshipList.addInternship(internship);
        assertEquals(3, internshipList.size());
    }

    @Test
    void testGetInternshipValidIndex() throws InvalidIndexException {
        Internship internship = internshipList.getInternship(0);
        assertEquals("Google", internship.getCompany());
    }

    @Test
    void testGetInternshipInvalidIndex() {
        assertThrows(InvalidIndexException.class, () -> internshipList.getInternship(5));
    }

    @Test
    void testDeleteInternshipValidIndex() throws InvalidIndexException {
        Internship deletedInternship = internshipList.deleteInternship(0);
        assertEquals("Google", deletedInternship.getCompany());
        assertEquals(1, internshipList.size());
    }

    @Test
    void testDeleteInternshipInvalidIndex() {
        assertThrows(InvalidIndexException.class, () -> internshipList.deleteInternship(5));
    }

    @Test
    void testGetAllInternships() {
        List<Internship> internships = internshipList.getAllInternships();
        assertEquals(2, internships.size());
    }

    @Test
    void testSize() {
        assertEquals(2, internshipList.size());
    }
}
