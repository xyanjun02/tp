package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;

public class AddCommand {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    public void execute() {
        String company = extractValue(input, "c/");
        String role = extractValue(input, "r/");
        String durationStr = extractValue(input, "d/");
        int duration = Integer.parseInt(durationStr);
        String salaryStr = extractValue(input, "s/");
        int salary = Integer.parseInt(salaryStr);
        String prerequisiteInput = extractValue(input, "pre/");
        String[] prereqs = prerequisiteInput.split(" ");
        String prereq1 = prereqs[0];
        String prereq2 = prereqs[1];
        String prereq3 = prereqs[2];

        Internship toAdd = new Internship(company, role, duration, salary, prereq1, prereq2, prereq3);
        NextStep.internships.add(toAdd);
        System.out.println(toAdd);
    }

    private String extractValue(String input, String prefix) {
        int startIndex = input.indexOf(prefix) + prefix.length();
        int endIndex = input.indexOf(" ", startIndex);
        if (prefix.equals("pre/")) {
            return input.substring(startIndex);
        }
        return input.substring(startIndex, endIndex);
    }
}
