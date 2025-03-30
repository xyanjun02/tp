package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.storage.Storage;
import seedu.nextstep.ui.Ui;

public class AddCommand extends Command {
    private final Storage storage;

    public AddCommand(String input, InternshipList internships, Storage storage) {
        super(input, internships);
        this.storage = storage;
    }

    /**
     * Executes the adding of a new Internship.
     * @throws EmptyInputException If no details are given after "add".
     * @throws InvalidInputFormatException If there are missing fields.
     * @throws NumberFormatException If allowance/duration are not integers.
     */
    @Override
    public void execute() throws EmptyInputException, InvalidInputFormatException{
        if (input.trim().equals("add")) {
            throw new EmptyInputException("Error: Please provide the details for the internship" +
                    " (e.g., c/, r/, d/, a/, s/).");
        }

        // Extract values for each field.
        String company = extractValue(input, "c/");
        String role = extractValue(input, "r/");
        String durationStr = extractValue(input, "d/");
        String allowanceStr = extractValue(input, "a/");
        String skillsInput = extractValue(input, "s/");

        // Validate that none of the required fields are empty.
        validateFields(company, role, durationStr, allowanceStr, skillsInput);

        int duration = Integer.parseInt(durationStr);
        int allowance = Integer.parseInt(allowanceStr);

        // Process skills: split by commas and trim each entry.
        String[] skills = processSkills(skillsInput);

        Internship internship = new Internship(company, role, duration, allowance, skills);

        // Assertions to verify key assumptions.
        assert !internship.getSkills().isEmpty() : "There should be at least one skill";

        internships.addInternship(internship);
        storage.save(internships);
        Ui.printAddingMessage(internship);
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

    private void validateFields(String... fields) throws InvalidInputFormatException {
        for (String field : fields) {
            if (field.isEmpty()) {
                throw new InvalidInputFormatException("Error: Missing parameters");
            }
        }
    }

    private String[] processSkills(String skillsInput) {
        String[] skills = skillsInput.split(",");
        for (int i = 0; i < skills.length; i++) {
            skills[i] = skills[i].trim();
        }
        return skills;
    }
}
