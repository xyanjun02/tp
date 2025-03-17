package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.ui.Ui;

public class ListCommand {

    public void execute() {
        if (NextStep.internships.isEmpty()) {
            System.out.println("No internships here (•-•). Try adding some!");
            Ui.printLinebreak();
            return;
        }
        System.out.println("Here is your list! (•ᴗ•)");
        Ui.printLinebreak();
        for (int i = 0; i < NextStep.internships.size(); i++) {
            System.out.println((i + 1) + ". " + NextStep.internships.get(i));
            Ui.printLinebreak();
        }
    }
}
