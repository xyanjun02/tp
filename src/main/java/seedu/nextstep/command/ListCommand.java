package seedu.nextstep.command;

import seedu.nextstep.core.InternshipList;
import seedu.nextstep.ui.Ui;
import seedu.nextstep.ui.TablePrinter;

/**
 * Represents a command that lists all available internships.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand instance.
     */
    public ListCommand(InternshipList internships) {
        super(null, internships);
    }

    /**
     * Executes the list command by displaying all stored internships.
     * If there are no internships, an appropriate message is displayed.
     */
    @Override
    public void execute() {
        if (internships.getAllInternships().isEmpty()) {
            Ui.printLinebreak();
            System.out.println("No internships here (0-0). Try adding some.");
            Ui.printLinebreak();
            return;
        }
        TablePrinter.printTable(internships.getAllInternships()); // Calls Table utility
    }
}
