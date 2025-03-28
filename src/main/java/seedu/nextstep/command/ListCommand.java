package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.ui.Ui;
import seedu.nextstep.ui.TablePrinter;
import static seedu.nextstep.NextStep.internships;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute() {
        if (NextStep.internships.isEmpty()) {
            Ui.printLinebreak();
            System.out.println("No internships here (•-•). Try adding some!");
            Ui.printLinebreak();
            return;
        }
        TablePrinter.printTable(internships); // Calls Table utility
    }
}
