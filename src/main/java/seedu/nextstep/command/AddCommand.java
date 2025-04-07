package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.InvalidIntegerException;
import seedu.nextstep.storage.Storage;
import seedu.nextstep.ui.Ui;

import java.util.HashSet;
import java.util.Set;

public class AddCommand extends Command {
    private static final int MAX_COMPANY_LENGTH = 70;
    private static final int MAX_ROLE_LENGTH = 50;
    private static final int MAX_DURATION = 24; // max 2 years internship
    private static final int MAX_ALLOWANCE = 999999; // max allowance limit
    private static final int MAX_SKILLS = 6; // max 6 skills

    private static final String[] VALID_FLAGS = {"c/", "r/", "d/", "a/", "s/", "st/"};

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

        validateFlags(input); // Validate flags (both invalid and duplicates)

        // Extract values for each field.
        String company = extractValue(input, "c/");
        String role = extractValue(input, "r/");
        String durationStr = extractValue(input, "d/");
        String allowanceStr = extractValue(input, "a/");
        String skillsInput = extractValue(input, "s/");
        String status = extractValue(input, "st/").toUpperCase();

        // Validate that none of the required fields are empty.
        validateFields(company, role, durationStr, allowanceStr, skillsInput, status);

        // Validate company and role length
        if (company.length() > MAX_COMPANY_LENGTH) {
            throw new InvalidInputFormatException("Error: Company name cannot exceed " + MAX_COMPANY_LENGTH +
                    " characters.");
        }
        if (role.length() > MAX_ROLE_LENGTH) {
            throw new InvalidInputFormatException("Error: Role name cannot exceed " + MAX_ROLE_LENGTH +
                    " characters.");
        }

        // Validate duration and allowance.
        int duration = Integer.parseInt(durationStr);
        if (duration <= 0 || duration > MAX_DURATION) {
            throw new InvalidIntegerException("Error: Duration must be greater than 0 and less than or equal to " +
                    MAX_DURATION + " months.");
        }
        int allowance = Integer.parseInt(allowanceStr);
        if (allowance < 0 || allowance > MAX_ALLOWANCE) {
            throw new InvalidIntegerException("Error: Allowance must be between 0 and " + MAX_ALLOWANCE + ".");
        }

        // Validate Status.
        if (!checkIsValidStatus(status)) {
            throw new InvalidInputFormatException("Error: Status must be 'A', 'P', 'R' or '-'.");
        }

        // Process skills: split by commas, trim each entry and check for skill limit
        String[] skills = processSkills(skillsInput);
        if (skills.length > MAX_SKILLS) {
            throw new InvalidInputFormatException("Error: You cannot have more than " + MAX_SKILLS + " skills.");
        }

        Internship internship = new Internship(company, role, duration, allowance, status, skills);

        // Assertions to verify key assumptions.
        assert !internship.getSkills().isEmpty() : "There should be at least one skill";

        internships.addInternship(internship);
        storage.save(internships);
        Ui.printAddingMessage(internship);
    }

    /**
     * Validates flags to check for invalid or duplicate flags.
     */
    private void validateFlags(String input) throws InvalidInputFormatException {
        Set<String> seenFlags = new HashSet<>();
        String[] words = input.split(" ");

        // Loop through all parts of the input to detect flags and ensure no unrecognized ones exist
        for (String word : words) {
            boolean isValidFlag = false;

            // Check if word is a valid flag
            for (String validFlag : VALID_FLAGS) {
                if (word.startsWith(validFlag)) {
                    if (!seenFlags.add(validFlag)) {
                        throw new InvalidInputFormatException("Error: Duplicate flag '" + validFlag +
                                "' detected. Each flag should appear only once.");
                    }
                    isValidFlag = true;
                    break;
                }
            }

            // If no valid flag is found, it's an unrecognized flag
            if (!isValidFlag && word.contains("/")) {
                throw new InvalidInputFormatException("Error: Unrecognized flag '" + word +
                        "'. Valid flags are: c/, r/, d/, a/, s/, st/.");
            }
        }
    }

    /**
     * Extracts the value associated with the specified prefix from the input.
     * If the prefix is not found, returns an empty string.
     * Warns if the same prefix appears multiple times.
     *
     * @param input  the full user input
     * @param prefix the prefix to look for (e.g., "c/", "r/")
     * @return the extracted value as a String
     */
    private String extractValue(String input, String prefix) {
        int startIndex;
        if (input.startsWith(prefix)) {
            startIndex = 0;
        } else {
            startIndex = input.indexOf(" " + prefix);
            if (startIndex != -1) {
                startIndex += 1; // skip the space
            }
        }

        if (startIndex == -1) {
            return "";
        }

        startIndex += prefix.length();

        int endIndex = input.length();
        for (String nextPrefix : VALID_FLAGS) {
            if (!nextPrefix.equals(prefix)) {
                int nextPrefixIndex = input.indexOf(" " + nextPrefix, startIndex);
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
    private boolean checkIsValidStatus(String status) {
        return status.equals("A") || status.equals("P") || status.equals("R") || status.equals("-");
    }

    private String[] processSkills(String skillsInput) {
        String[] skills = skillsInput.split(",");
        for (int i = 0; i < skills.length; i++) {
            skills[i] = skills[i].trim();
        }
        return skills;
    }
}
