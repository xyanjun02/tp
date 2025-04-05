package seedu.nextstep.command;

import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.exception.InvalidIntegerException;

/**
 * Represents a parent Command to be inherited by other classes.
 * Provides constructors for commands that require input and commands that do not require input.
 * Provides a method to execute.
 */
public abstract class Command {
    protected String input;
    protected InternshipList internships;

    /**
     * Main constructor for commands that require input.
     *
     * @param input       the user input.
     * @param internships the internship list.
     */
    public Command(String input, InternshipList internships) {
        this.input = input;
        this.internships = internships;
    }

    /**
     * Constructor for commands that do not require input.
     *
     * @param internships the internship list.
     */
    public Command(InternshipList internships) {
        this(null, internships);
    }

    /**
     * Executes the command.
     *
     * @throws EmptyInputException         if input is empty.
     * @throws InvalidInputFormatException if input format is invalid.
     * @throws InvalidIndexException       if an invalid index is used.
     * @throws InvalidIntegerException     if an invalid integer is provided.
     *
     */
    public abstract void execute()
            throws EmptyInputException, InvalidInputFormatException, InvalidIndexException, InvalidIntegerException;
}
