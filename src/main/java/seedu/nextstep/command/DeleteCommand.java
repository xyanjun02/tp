package seedu.nextstep.command;

import java.util.logging.Logger;
import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;
import seedu.nextstep.exception.CommandException;
import seedu.nextstep.ui.Ui;

public class DeleteCommand {
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    private final String input;

    public DeleteCommand(String input) {
        // Assert that input is not null or empty
        assert input != null && !input.trim().isEmpty() : "Input should not be null or empty";
        this.input = input;
    }

    public void execute() {
        logger.info("Executing DeleteCommand with input: " + input);
        try {
            String[] tokens = input.split(" ");
            if (tokens.length < 2) {
                throw new CommandException("Error: Missing index. Use: delete <index>");
            }
            int index = Integer.parseInt(tokens[1]) - 1; // Convert to 0-based index
            if (index < 0 || index >= NextStep.internships.size()) {
                throw new CommandException("Invalid index. Please enter a valid internship number.");
            }
            Internship removed = NextStep.internships.remove(index);
            // Assert that the removed internship is not null
            assert removed != null : "Removed internship should not be null";
            Ui.printDeleteSuccess(removed);
            logger.info("Successfully deleted internship: " + removed);
        } catch (NumberFormatException e) {
            logger.warning("NumberFormatException in DeleteCommand: " + e.getMessage());
            System.out.println("Error: Invalid index format. Use: delete <index>");
        } catch (CommandException e) {
            logger.warning("CommandException in DeleteCommand: " + e.getMessage());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            logger.severe("Unexpected exception in DeleteCommand: " + e.getMessage());
            System.out.println("Error: Invalid input. Use: delete <index>");
        }
    }
}


