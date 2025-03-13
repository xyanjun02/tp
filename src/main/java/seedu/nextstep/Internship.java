package seedu.nextstep;

import java.util.Arrays;

public class Internship {
    protected String company;
    protected String role;
    protected int duration; // in months
    protected int salary;
    protected String[] skills;

    public Internship(String company, String role, int duration, int salary, String skill1, String skill2, String skill3) {
        this.company = company;
        this.role = role;
        this.duration = duration;
        this.salary = salary;
        this.skills = new String[]{skill1, skill2, skill3};
    }

    @Override
    public String toString() {
        return "Company: " + company + System.lineSeparator() +
                "Role: " + role + System.lineSeparator() +
                "Duration: " + duration + " months" +  System.lineSeparator() +
                "Salary: $" + salary + System.lineSeparator() +
                "Skills: " + skills[0] + ", " + skills[1] + ", " + skills[2] + System.lineSeparator();
    }
}
