package seedu.nextstep.command;

/**
 * Represents a parent Command to be inherited by other classes.
 * Provides constructors for commands that require input and commands that do not require input.
 * Provides a method to execute.
 */
public class Command {
    protected String input;

    // Main constructor for commands that require input
    public Command(String input) {
        assert input != null && !input.trim().isEmpty() : "Input must not be null or empty";
        this.input = input;
    }

    // Constructor for commands that do not require input
    public Command() {
        this.input = null;
    }

    // to be overridden
    public void execute() {

    }
}
