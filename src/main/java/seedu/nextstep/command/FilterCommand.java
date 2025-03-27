package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;
import seedu.nextstep.ui.Ui;

/**
 * Executes filtering of internships based on user input and prints filtered internships.
 * Able to filter via allowance and internship.
 * If user enters one number - filters internships that have an allowance/duration higher than the input.
 * If user enters two numbers - filters internships that have an allowance/duration in between the 2 inputs.
 */
public class FilterCommand extends Command {
    public FilterCommand(String input) {
        super(input);
    }

    @Override
    public void execute() {
        String[] words = input.split(" ");
        String filterType = words[0];
        int minVal = Integer.parseInt(words[1]);
        int maxVal;
        if (words.length == 2) {
            maxVal = -1;
        } else {
            maxVal = Integer.parseInt(words[2]);
        }
        for (Internship internship : NextStep.internships) {
            if (isWithinRange(internship, filterType, minVal, maxVal)) {
                Ui.printInternship(internship);
                Ui.printLinebreak();
            }
        }
    }

    /**
     * Helper function to check whether an internship is within the range of the user input.
     * @param internship The internship to be checked.
     * @param filterType The field to be filtered i.e. allowance or duration.
     * @param minValue The minimum value .
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
