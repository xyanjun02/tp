package seedu.nextstep.command;

import seedu.nextstep.ui.Ui;

/**
 * Represents a command that displays help instructions for the available commands in the application.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a HelpCommand instance.
     */
    public HelpCommand() {
        super(null, null);
    }

    /**
     * Executes the help command by printing out the list of available commands.
     */
    @Override
    public void execute() {
        // Show the help instructions for the commands
        Ui.printLinebreak();
        System.out.println("Do help yourself to these commands! (0u0)");
        Ui.printLinebreak();
        System.out.println("1. add [c/COMPANY] [r/ROLE] [d/DURATION] [a/ALLOWANCE] [s/SKILLS] [st/STATUS] " +
                "- Adds a new internship");
        System.out.println("2. delete [INDEX] - Deletes an internship at the specified index");
        System.out.println("3. list - Lists all available internships");
        System.out.println("4. find/s [SKILL] - Finds internships with specific skill(s)");
        System.out.println("5. find/r [ROLE] - Finds internships with one or more specified role(s)");
        System.out.println("6. find/c [COMPANY] - Finds internships with a specific company");
        System.out.println("7. filter/a [MIN_VAL] [MAX_VAL] - Finds internships with allowances within range. " +
                "If no max is provided, finds those above min.");
        System.out.println("8. filter/d [MIN_VAL] [MAX_VAL] - Finds internships with durations within range. " +
                "If no max is provided, finds those above min.");
        System.out.println("9. edit [INDEX] - Edits an internship at the specified index");
        System.out.println("10. bye - Exits the application");
        Ui.printLinebreak();
    }
}
