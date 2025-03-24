package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.exception.CommandException;
import seedu.nextstep.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute() {
        try {
            if (NextStep.internships.isEmpty()) {
                throw new CommandException("No internships here (•-•). Try adding some!");
            }
            System.out.println("Here is your list! (•ᴗ•)");
            Ui.printLinebreak();
            for (int i = 0; i < NextStep.internships.size(); i++) {
                System.out.println((i + 1) + ". " + NextStep.internships.get(i));
                Ui.printLinebreak();
            }
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Unable to list internships due to an unexpected error.");
        }
    }
}
