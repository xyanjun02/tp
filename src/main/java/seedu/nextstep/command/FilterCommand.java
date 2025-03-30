package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.ui.Ui;

/**
 * Filters internships based on user input and prints filtered internships.
 * Able to filter via allowance and internship.
 */
public class FilterCommand extends Command {
    public FilterCommand(String input, InternshipList internships) {
        super(input, internships);
    }

    /**
     * Executes the filtering.
     * If user enters one number - filters internships that have an allowance/duration higher than the input.
     * If user enters two numbers - filters internships that have an allowance/duration in between the 2 inputs.
     * @throws EmptyInputException If no range is given by the user.
     * @throws InvalidInputFormatException If user inputs more than 2 values.
     * @throws NumberFormatException If ranges provided are not integers.
     */
    @Override
    public void execute() throws EmptyInputException, InvalidInputFormatException, NumberFormatException {
        // Check for completely missing filter details
        if (input.trim().equals("filter/a") || input.trim().equals("filter/d")) {
            throw new EmptyInputException("Error: Please provide the details for the filter (e.g., MIN_VAL, MAX_VAL).");
        }

        String[] words = input.split(" ");
        // Ensure we have at least a filter command and a min value
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new EmptyInputException("Error: Missing minimum value for filtering.");
        }

        String filterType = words[0];
        int minVal;
        int maxVal = -1; // default when only one number is provided

        try {
            minVal = Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: Minimum value must be an integer.");
        }

        // Check for a maximum value if provided
        if (words.length >= 3) {
            if (words[2].trim().isEmpty()) {
                throw new EmptyInputException("Error: Maximum value is empty. Please provide a valid number or omit it.");
            }
            if (words.length == 3) {
                try {
                    maxVal = Integer.parseInt(words[2]);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Error: Maximum value must be an integer.");
                }
            } else {
                throw new InvalidInputFormatException("Error: Please only provide 1 or 2 values.");
            }
        }

        boolean found = false;
        for (Internship internship : internships.getAllInternships()) {
            if (isWithinRange(internship, filterType, minVal, maxVal)) {
                Ui.printInternship(internship);
                Ui.printLinebreak();
                found = true;
            }
        }
        if (!found) {
            Ui.printNoFilteredInternshipFound();
        }
    }


    /**
     * Helper function to check whether an internship is within the range of the user input.
     * @param internship The internship to be checked.
     * @param filterType The field to be filtered i.e. allowance or duration.
     * @param minValue The minimum value.
     * @param maxValue The maximum value - set to -1 if user only inputs 1 number.
     * @return True if the internship is within the given range, false otherwise.
     */
    private boolean isWithinRange(Internship internship, String filterType, int minValue, int maxValue) {
        if (filterType.equals("filter/a")) {
            int allowance = internship.getAllowance();
            if (maxValue < 0) {
                return allowance >= minValue;
            }
            return allowance >= minValue && allowance <= maxValue;
        } else if (filterType.equals("filter/d")) {
            int duration = internship.getDuration();
            if (maxValue < 0) {
                return duration >= minValue;
            }
            return duration >= minValue && duration <= maxValue;
        } else {
            return false;
        }
    }
}
