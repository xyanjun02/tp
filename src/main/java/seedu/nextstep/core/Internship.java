package seedu.nextstep.core;

import java.util.ArrayList;
import java.util.List;

public class Internship {
    protected String company;
    protected String role;
    protected int duration; // in months
    protected int allowance;
    private final String[] skills;

    public Internship(String company, String role, int duration, int allowance, String... skills) {
        this.company = company;
        this.role = role;
        this.duration = duration;
        this.allowance = allowance;
        this.skills = skills;
    }

    public List<String> getSkills() {
        return List.of(skills);
    }

    @Override
    public String toString() {
        return "Company: " + company + System.lineSeparator() +
                "Role: " + role + System.lineSeparator() +
                "Duration: " + duration + " months" + System.lineSeparator() +
                "Allowance: $" + allowance + System.lineSeparator() +
                "Skills: " + String.join(", ", skills);
    }
}
