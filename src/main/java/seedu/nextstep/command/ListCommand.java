package seedu.nextstep.command;

import seedu.nextstep.exception.CommandException;
import seedu.nextstep.ui.TablePrinter;

import static seedu.nextstep.NextStep.internships;
import static seedu.nextstep.ui.Ui.printLinebreak;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute() {
        try {
            if (internships.isEmpty()) {
                printLinebreak();
                throw new CommandException("No internships here (•-•). Try adding some!");
            }

            TablePrinter.printTable(internships); // Calls Table utility
        } catch (CommandException e) {
            System.out.println(e.getMessage());
            printLinebreak();
        } catch (Exception e) {
            System.out.println("Error: Unable to list internships due to an unexpected error.");
        }
    }
}
