package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.storage.Storage;
import seedu.nextstep.ui.Ui;

public class DeleteCommand extends Command {
    private final Storage storage;

    public DeleteCommand(String input, InternshipList internships, Storage storage) {
        super(input, internships);
        this.storage = storage;
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
        Internship removed = internships.deleteInternship(index);

        // Assert that the removed internship is not null.
        assert removed != null : "Removed internship should not be null";
        storage.save(internships);
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

        try {
            int index = Integer.parseInt(tokens[1]) - 1;
            if (index < 0 || index >= internships.size()) {
                throw new InvalidIndexException("Invalid index. Please enter a valid internship number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException("Index must be a number");
        }
    }
}
