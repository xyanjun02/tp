package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;
import seedu.nextstep.exception.CommandException;
import seedu.nextstep.ui.Ui;

public class FindSkillCommand extends Command {

    public FindSkillCommand(String input) {
        super(input);
    }

    @Override
    public void execute() {
        try {
            // Split the input into two parts: command and skill parameter.
            String[] parts = input.split("find/s", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CommandException("Error: Please specify a skill after '/s'.");
            }

            String skill = parts[1].trim();
            Ui.printSearchingForSkill(skill);
            boolean found = false;

            // Search each internship's skills for a case-insensitive match.
            for (Internship internship : NextStep.internships) {
                for (String s : internship.getSkills()) {
                    if (s.equalsIgnoreCase(skill)) {
                        Ui.printInternship(internship);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                Ui.printNoInternshipFound(skill);
            }
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }
}
