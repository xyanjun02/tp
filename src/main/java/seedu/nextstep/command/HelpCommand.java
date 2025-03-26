package seedu.nextstep.command;

import seedu.nextstep.ui.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public void execute() {
        // Show the help instructions for the commands
        System.out.println("Do help yourself to these commands! (•ᴗ•)");
        Ui.printLinebreak();
        System.out.println("1. add [c/COMPANY] [r/ROLE] [d/DURATION] [a/ALLOWANCE] [s/SKILLS]- Adds a new internship");
        System.out.println("2. delete [INDEX] - Deletes an internship at the specified index");
        System.out.println("3. list - Lists all available internships");
        System.out.println("4. find/s [SKILL] - Finds internships with specific skill");
        System.out.println("5. bye - Exit the application");
        Ui.printLinebreak();
    }
}
