package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;
import seedu.nextstep.ui.Ui;
import seedu.nextstep.exception.CommandException;

public class AddCommand extends Command {

    public AddCommand(String input) {
        super(input);
    }

    /**
     * Executes the adding of a new Internship.
     * Handles all exceptions internally.
     */
    @Override
    public void execute() {
        try {
            if (input.trim().equals("add")) {
                throw new CommandException("Error: Please provide the details for the internship" +
                        " (e.g., c/, r/, d/, a/, s/).");
            }

            // Extract values for each field.
            String company = extractValue(input, "c/");
            String role = extractValue(input, "r/");
            String durationStr = extractValue(input, "d/");
            String allowanceStr = extractValue(input, "a/");
            String skillsInput = extractValue(input, "s/");

            // Validate that none of the required fields are empty.
            if (company.isEmpty() || role.isEmpty() || durationStr.isEmpty() ||
                    allowanceStr.isEmpty() || skillsInput.isEmpty()) {
                throw new CommandException("Error: Missing parameters. Please ensure all fields are provided.");
            }

            int duration;
            int allowance;
            try {
                // Parse numeric values only after confirming they are provided.
                duration = Integer.parseInt(durationStr);
                allowance = Integer.parseInt(allowanceStr);
            } catch (NumberFormatException e) {
                throw new CommandException("Error: Invalid number format. Please check the duration & salary values.");
            }

            // Process skills: split by commas and trim each entry.
            String[] skills = skillsInput.split(",");
            for (int i = 0; i < skills.length; i++) {
                skills[i] = skills[i].trim();
            }
            if (skills.length == 0 || (skills.length == 1 && skills[0].isEmpty())) {
                throw new CommandException("Error: Please provide at least one skill in the 's/' section.");
            }

            // Create the internship and add it to the list.
            Internship toAdd = new Internship(company, role, duration, allowance, skills);
            // Assertions to verify key assumptions.
            assert toAdd != null : "Internship object should not be null";
            assert !toAdd.getSkills().isEmpty() : "There should be at least one skill";

            NextStep.internships.add(toAdd);
            Ui.printAddingMessage(toAdd);
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Unable to add internship. Please check the input format.");
        }
    }

    /**
     * Extracts the value associated with the specified prefix from the input.
     * If the prefix is not found, returns an empty string.
     *
     * @param input  the full user input
     * @param prefix the prefix to look for (e.g., "c/", "r/")
     * @return the extracted value as a String
     */
    private String extractValue(String input, String prefix) {
        int startIndex = input.indexOf(prefix);
        if (startIndex == -1) {
            return "";
        }
        startIndex += prefix.length();

        int endIndex = input.length();
        // Look for the earliest occurrence of any other known prefix.
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
