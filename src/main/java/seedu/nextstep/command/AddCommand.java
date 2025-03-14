package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;

public class AddCommand {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    public void execute() {
        if (input.trim().equals("add")) {
            System.out.println("Error: Please provide the details for the internship (e.g., c/, r/, d/, s/, pre/).");
            return;
        }

        try {
            String company = extractValue(input, "c/");
            String role = extractValue(input, "r/");
            String durationStr = extractValue(input, "d/");
            int duration = Integer.parseInt(durationStr);
            String salaryStr = extractValue(input, "s/");
            int salary = Integer.parseInt(salaryStr);
            String prerequisiteInput = extractValue(input, "pre/");

            if (company.isEmpty() || role.isEmpty() || durationStr.isEmpty() || salaryStr.isEmpty() || prerequisiteInput.isEmpty()) {
                System.out.println("Error: Missing required parameters. Please ensure all fields are provided.");
                return;
            }

            String[] prereqs = prerequisiteInput.split(" ");
            if (prereqs.length < 3) {
                System.out.println("Error: Please provide 3 skills in the 'pre/' section.");
                return;
            }

            String prereq1 = prereqs[0];
            String prereq2 = prereqs[1];
            String prereq3 = prereqs[2];

            Internship toAdd = new Internship(company, role, duration, salary, prereq1, prereq2, prereq3);
            NextStep.internships.add(toAdd);
            System.out.println("Internship Added: " + toAdd);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please check the duration and salary values.");
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }

    private String extractValue(String input, String prefix) {
        int startIndex = input.indexOf(prefix) + prefix.length();
        if (startIndex == -1 || startIndex >= input.length()) {
            return "";
        }
        int endIndex = input.indexOf(" ", startIndex);
        if (endIndex == -1) {
            endIndex = input.length();
        }
        return input.substring(startIndex, endIndex);
    }
}
