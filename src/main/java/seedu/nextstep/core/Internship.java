package seedu.nextstep.core;

import java.io.Serializable;
import java.util.List;

/**
 * Represents an internship with company details, role, duration, allowance, and required skills.
 */
public class Internship implements Serializable {
    protected String company;
    protected String role;
    protected int duration; // in months
    protected int allowance;
    private String[] skills;

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
     * Sets the company offering the internship.
     *
     * @param company The new company name.
     */
    public void setCompany(String company) {
        this.company = company;
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
     * Sets the role of the internship.
     *
     * @param role The new role title.
     */
    public void setRole(String role) {
        this.role = role;
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
     * Sets the duration of the internship in months.
     *
     * @param duration The new duration in months.
     */
    public void setDuration(int duration) {
        this.duration = duration;
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
     * Sets the monthly allowance for the internship.
     *
     * @param allowance The new allowance amount.
     */
    public void setAllowance(int allowance) {
        this.allowance = allowance;
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
     * Sets the skills required for the internship.
     *
     * @param skills The new list of required skills.
     */
    public void setSkills(String... skills) {
        this.skills = skills;
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
