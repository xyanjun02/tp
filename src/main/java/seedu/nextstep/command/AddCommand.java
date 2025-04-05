package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.InvalidIntegerException;
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
     * @throws InvalidIntegerException If allowance is negative or duration is non-positive.
     */
    @Override
    public void execute() throws EmptyInputException, InvalidInputFormatException, InvalidIntegerException {
        if (input.trim().equals("add")) {
            throw new EmptyInputException("Error: Please provide the details for the internship" +
                    " (e.g. c/, r/, d/, a/, s/, st/).");
        }

        // Extract values for each field.
        String company = extractValue(input, "c/");
        String role = extractValue(input, "r/");
        String durationStr = extractValue(input, "d/");
        String allowanceStr = extractValue(input, "a/");
        String skillsInput = extractValue(input, "s/");
        String status = extractValue(input, "st/").toUpperCase();

        // Validate that none of the required fields are empty.
        validateFields(company, role, durationStr, allowanceStr, skillsInput, status);

        // Validate duration and allowance.
        int duration = Integer.parseInt(durationStr);
        if (duration <= 0) {
            throw new InvalidIntegerException("Error: Duration must be greater than 0.");
        }
        int allowance = Integer.parseInt(allowanceStr);
        if (allowance < 0) {
            throw new InvalidIntegerException("Error: Allowance cannot be negative.");
        }

        // Validate Status.
        if (!isValidStatus(status)) {
            throw new InvalidInputFormatException("Error: Status must be 'A', 'P', 'R' or '-'.");
        }

        // Process skills: split by commas and trim each entry.
        String[] skills = processSkills(skillsInput);

        Internship internship = new Internship(company, role, duration, allowance, status, skills);

        // Assertions to verify key assumptions.
        assert internship != null;
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
        for (String nextPrefix : new String[]{"c/", "r/", "d/", "a/", "s/", "st/"}) {
            if (!nextPrefix.equals(prefix)) {
                int nextPrefixIndex = input.indexOf(nextPrefix, startIndex);
                if (nextPrefixIndex != -1 && nextPrefixIndex < endIndex) {
                    endIndex = nextPrefixIndex;
                }
            }
        }
        return input.substring(startIndex, endIndex).trim();
    }

    /**
     * Checks if any field is empty
     * @param fields The fields entered.
     * @throws InvalidInputFormatException If any field is empty.
     */
    private void validateFields(String... fields) throws InvalidInputFormatException {
        for (String field : fields) {
            if (field.isEmpty()) {
                throw new InvalidInputFormatException("Error: Missing parameters (e.g. c/, r/, d/, a/, s/, st/).");
            }
        }
    }

    /**
     * Checks if status provided is valid.
     * @param status The status to be checked.
     * @return true is status is valid, false otherwise.
     */
    private boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("A") || status.equalsIgnoreCase("P") ||
                status.equalsIgnoreCase("R") || status.equalsIgnoreCase("-");
    }

    private String[] processSkills(String skillsInput) {
        String[] skills = skillsInput.split(",");
        for (int i = 0; i < skills.length; i++) {
            skills[i] = skills[i].trim();
        }
        return skills;
    }
}
