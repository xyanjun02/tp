/*
 * FindSkillCommand.java
 *
 * This class defines the FindSkillCommand used to search for internships
 * that match one or more skills provided by the user. The command allows for
 * multiple skills separated by commas and prints a line break between each result.
 */

package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;
import seedu.nextstep.exception.CommandException;
import seedu.nextstep.ui.Ui;

/**
 * The FindSkillCommand class processes user input to search for internships
 * that contain any of the specified skills. Skills can be provided as a comma-separated
 * list after the command prefix "find/s".
 */
public class FindSkillCommand extends Command {

    /**
     * Constructs a new FindSkillCommand with the specified input.
     *
     * @param input the user input containing the command and skill(s)
     */
    public FindSkillCommand(String input) {
        super(input);
    }

    /**
     * Executes the find skill command by extracting the skill parameters,
     * splitting them into individual skills, and searching the internships list for
     * any matching skills. If a match is found, the internship details are printed
     * along with a line break after each result. If no internships match the search
     * criteria, an appropriate message is displayed.
     */
    @Override
    public void execute() {
        try {
            // Split the input into two parts: command and skill parameter.
            String[] parts = input.split("find/s", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CommandException("Error: Please specify at least one skill after '/s'.");
            }

            // Allow multiple skills, separated by commas.
            String searchSkillsStr = parts[1].trim();
            String[] searchSkills = searchSkillsStr.split(",");
            for (int i = 0; i < searchSkills.length; i++) {
                searchSkills[i] = searchSkills[i].trim();
            }

            // Print which skills are being searched for.
            Ui.printSearchingForSkill(String.join(", ", searchSkills));

            boolean found = false;

            // Search each internship's skills for any match among the search skills.
            for (Internship internship : NextStep.internships) {
                boolean matchFound = false;
                for (String searchSkill : searchSkills) {
                    for (String s : internship.getSkills()) {
                        if (s.equalsIgnoreCase(searchSkill)) {
                            matchFound = true;
                            break;
                        }
                    }
                    if (matchFound) {
                        break;
                    }
                }
                if (matchFound) {
                    Ui.printInternship(internship);
                    // Add a line break between each result.
                    Ui.printLinebreak();
                    found = true;
                }
            }

            if (!found) {
                Ui.printNoInternshipFound(String.join(", ", searchSkills));
            }
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }
}
