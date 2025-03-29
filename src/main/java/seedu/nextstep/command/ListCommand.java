package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.ui.Ui;
import seedu.nextstep.ui.TablePrinter;
import static seedu.nextstep.NextStep.internships;

/**
 * Represents a command that lists all available internships.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand instance.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the list command by displaying all stored internships.
     * If there are no internships, an appropriate message is displayed.
     */
    @Override
    public void execute() {
        if (NextStep.internships.isEmpty()) {
            Ui.printLinebreak();
            System.out.println("No internships here (0-0). Try adding some!");
            Ui.printLinebreak();
            return;
        }
        TablePrinter.printTable(internships); // Calls Table utility
    }
}
