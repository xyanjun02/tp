package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;
import seedu.nextstep.ui.Ui;
import seedu.nextstep.exception.CommandException;

public class DeleteCommand extends Command {

    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the deletion of an internship.
     * Handles all exceptions internally.
     */
    @Override
    public void execute() {
        try {
            int index = parseIndex(input);
            Internship removed = NextStep.internships.remove(index);
            // Assert that the removed internship is not null.
            assert removed != null : "Removed internship should not be null";
            Ui.printDeleteSuccess(removed);
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Unable to delete internship. Please check the input format.");
        }
    }

    /**
     * Extracts and validates the index from the input.
     *
     * @param input the full user input.
     * @return the 0-based index.
     * @throws CommandException if the index is missing, not a number, or out of bounds.
     */
    private int parseIndex(String input) throws CommandException {
        String[] tokens = input.split(" ");
        if (tokens.length < 2) {
            throw new CommandException("Error: Missing index. Use: delete <index>");
        }
        try {
            int index = Integer.parseInt(tokens[1]) - 1; // Convert from 1-based to 0-based index.
            if (index < 0 || index >= NextStep.internships.size()) {
                throw new CommandException("Invalid index. Please enter a valid internship number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new CommandException("Error: Invalid index format. Use: delete <index>");
        }
    }
}
