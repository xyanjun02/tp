package seedu.nextstep.exception;

/**
 * A custom exception class for command-related errors.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
