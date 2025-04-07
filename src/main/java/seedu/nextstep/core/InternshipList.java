package seedu.nextstep.core;

import seedu.nextstep.exception.InvalidIndexException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InternshipList implements Serializable {
    private final ArrayList<Internship> internships;

    public InternshipList() {
        this.internships = new ArrayList<>();
    }

    public void addInternship(Internship internship) {
        internships.add(internship);
    }

    public Internship getInternship(int index) throws InvalidIndexException {
        if (index < 0 || index >= internships.size()) {
            throw new InvalidIndexException("Invalid Index: " + index);
        }
        return internships.get(index);
    }

    public Internship deleteInternship(int index) throws InvalidIndexException {
        if (index < 0 || index >= internships.size()) {
            throw new InvalidIndexException("Invalid Index: " + index);
        }
        return internships.remove(index);
    }

    public List<Internship> getAllInternships() {
        return new ArrayList<>(internships);
    }

    public int size() {
        return internships.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean contains(Internship internship) {
        for (Internship existing : internships) {
            if (existing.equals(internship)) {
                return true;
            }
        }
        return false;
    }
}
