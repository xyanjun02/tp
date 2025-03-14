package seedu.nextstep.command;

import seedu.nextstep.NextStep;

public class ListCommand {

    public void execute() {
        if (NextStep.internships.isEmpty()) {
            System.out.println("No internships available.");
            return;
        }
        System.out.println("Internship List:");
        for (int i = 0; i < NextStep.internships.size(); i++) {
            System.out.println((i + 1) + ". " + NextStep.internships.get(i));
        }
    }
}
