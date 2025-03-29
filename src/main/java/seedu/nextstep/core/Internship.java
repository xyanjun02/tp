package seedu.nextstep.core;

import java.util.List;

/**
 * Represents an internship with company details, role, duration, allowance, and required skills.
 */
public class Internship {
    protected String company;
    protected String role;
    protected int duration; // in months
    protected int allowance;
    private final String[] skills;

    /**
     * Constructs an Internship instance.
     *
     * @param company  The company offering the internship.
     * @param role     The role of the internship.
     * @param duration The duration of the internship in months.
     * @param allowance The monthly allowance for the internship.
     * @param skills   The skills required for the internship.
     */
    public Internship(String company, String role, int duration, int allowance, String... skills) {
        this.company = company;
        this.role = role;
        this.duration = duration;
        this.allowance = allowance;
        this.skills = skills;
    }

    /**
     * Gets the company offering the internship.
     *
     * @return The company name.
     */
    public String getCompany() {
        return company;
    }

    /**
     * Gets the role of the internship.
     *
     * @return The role title.
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the duration of the internship in months.
     *
     * @return The duration of the internship.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the monthly allowance for the internship.
     *
     * @return The allowance amount.
     */
    public int getAllowance() {
        return allowance;
    }

    /**
     * Gets the list of skills required for the internship.
     *
     * @return A list of skills.
     */
    public List<String> getSkills() {
        return List.of(skills);
    }

    /**
     * Returns a string representation of the internship details.
     *
     * @return A formatted string containing internship details.
     */
    @Override
    public String toString() {
        return "--------------------------------\n" +
                "Company   : " + company + System.lineSeparator() +
                "Role      : " + role + System.lineSeparator() +
                "Duration  : " + duration + " months" + System.lineSeparator() +
                "Allowance : $" + allowance + System.lineSeparator() +
                "Skills    : " + String.join(", ", skills) + System.lineSeparator() +
                "--------------------------------";
    }
}
