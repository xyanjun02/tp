package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;

public class AddCommand {
    private final String input;

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

            // Split the skills by commas, trim spaces, and filter out empty entries
            String[] prereqs = prerequisiteInput.split(",");
            for (int i = 0; i < prereqs.length; i++) {
                prereqs[i] = prereqs[i].trim();
            }

            if (prereqs.length == 0) {
                System.out.println("Error: Please provide at least one skill in the 'pre/' section.");
                return;
            }

            Internship toAdd = new Internship(company, role, duration, salary, prereqs);
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

