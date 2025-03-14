package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.NextStep;

public class DeleteCommand {
    private final String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    public void execute() {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1; // Convert to 0-based index
            if (index < 0 || index >= NextStep.internships.size()) {
                System.out.println("Invalid index. Please enter a valid internship number.");
                return;
            }
            Internship removed = NextStep.internships.remove(index);
            System.out.println("Deleted Internship: " + removed);
        } catch (Exception e) {
            System.out.println("Invalid input. Use: delete <index>");
        }
    }
}
