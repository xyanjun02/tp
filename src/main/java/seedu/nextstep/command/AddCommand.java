package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;
import seedu.nextstep.ui.Ui;

/**
 * Class to handle the adding of a new Internship into the internships ArrayList
 */
public class AddCommand {
    private final String input;

    /**
     * Constructs a new AddCommand class with the given String input.
     * @param input
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the adding of a new Internship.
     */
    public void execute() {
        if (input.trim().equals("add")) {
            System.out.println("Error: Please provide the details for the internship (e.g., c/, r/, d/, a/, s/).");
            return;
        }

        try {
            String company = extractValue(input, "c/");
            String role = extractValue(input, "r/");
            String durationStr = extractValue(input, "d/");
            int duration = Integer.parseInt(durationStr);
            String allowanceStr = extractValue(input, "a/");
            int allowance = Integer.parseInt(allowanceStr);
            String skillsInput = extractValue(input, "s/");

            if (company.isEmpty() || role.isEmpty() || durationStr.isEmpty() || allowanceStr.isEmpty() || skillsInput.isEmpty()) {
                System.out.println("Error: Missing required parameters. Please ensure all fields are provided.");
                return;
            }

            // Split the skills by commas, trim spaces, and filter out empty entries
            String[] skills = skillsInput.split(" ");
            for (int i = 0; i < skills.length; i++) {
                skills[i] = skills[i].trim();
            }

            if (skills.length == 0) {
                System.out.println("Error: Please provide at least one skill in the 's/' section.");
                return;
            }

            Internship toAdd = new Internship(company, role, duration, allowance, skills);
            assert toAdd != null: "Internship should be succesfully added";
            assert !toAdd.getSkills().isEmpty() : "There should be at least one skill";
            NextStep.internships.add(toAdd);
            Ui.printAddingMessage(toAdd);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please check the duration and salary values.");
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }

    /**
     * Returns a given internship field in String format.
     * @param input The input containing the internship fields.
     * @param prefix The prefix corresponding to the field being extracted.
     * @return The field in String format.
     */
    private String extractValue(String input, String prefix) {
        int startIndex = input.indexOf(prefix);
        if (startIndex == -1) {
            return ""; // prefix not found
        }
        startIndex += prefix.length();

        int endIndex = input.length();
        for (String nextPrefix : new String[]{"c/", "r/", "d/", "a/", "s/"}) {
            if (!nextPrefix.equals(prefix)) {
                int nextPrefixIndex = input.indexOf(nextPrefix, startIndex);
                if (nextPrefixIndex != -1 && nextPrefixIndex < endIndex) {
                    endIndex = nextPrefixIndex;
                }
            }
        }

        return input.substring(startIndex, endIndex).trim();
    }
}

