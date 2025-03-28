package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the deletion of an internship.
     * @throws EmptyInputException If no index is given.
     * @throws InvalidIndexException If index given is out of bounds.
     * @throws NumberFormatException If index given is not an integer.
     */
    @Override
    public void execute() throws EmptyInputException, InvalidIndexException, InvalidInputFormatException,
                        NumberFormatException {
        int index = parseIndex(input);
        Internship removed = NextStep.internships.remove(index);

        // Assert that the removed internship is not null.
        assert removed != null : "Removed internship should not be null";
        Ui.printDeleteSuccess(removed);
    }

    /**
     * Extracts and validates the index from the input.
     *
     * @param input the full user input.
     * @return the 0-based index
     * @throws EmptyInputException If no index is given.
     * @throws InvalidIndexException If index given is out of bounds.
     * @throws NumberFormatException If index given is not an integer.
     */
    private int parseIndex(String input) throws EmptyInputException, InvalidIndexException, InvalidInputFormatException,
                                        NumberFormatException {
        String[] tokens = input.split(" ");
        if (tokens.length < 2) {
            throw new EmptyInputException("Error: Missing index. Use: delete <index>");
        }
        if (tokens.length > 2) {
            throw new InvalidInputFormatException("Error: Too many indexes. Use: delete <index>");
        }
        int index = Integer.parseInt(tokens[1]) - 1; // Convert from 1-based to 0-based index.
        if (index < 0 || index >= NextStep.internships.size()) {
            throw new InvalidIndexException("Invalid index. Please enter a valid internship number.");
        }
        return index;
    }
}
