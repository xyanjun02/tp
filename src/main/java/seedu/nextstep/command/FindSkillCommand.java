package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.ui.Ui;

/**
 * The FindSkillCommand class processes user input to search for internships that contain any of the specified skills.
 * Skills can be provided as a comma-separated list after the command prefix "find/s".
 */
public class FindSkillCommand extends Command {

    /**
     * Constructs a new FindSkillCommand with the specified input.
     *
     * @param input       the user input containing the command and skill(s)
     * @param internships the internship list
     */
    public FindSkillCommand(String input, InternshipList internships) {
        super(input, internships);
    }

    /**
     * Executes the find skill command by extracting the skill parameters, splitting them into individual skills,
     * and searching the internships list for any matching skills.
     * If a match is found, the internship details are printed
     * along with a line break after each result. If no internships match the search criteria,
     * an appropriate message is displayed.
     *
     * @throws EmptyInputException if no skills are provided after the command prefix.
     */
    @Override
    public void execute() throws EmptyInputException {
        String[] parts = input.split("find/s", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyInputException("Error: Please specify at least one skill after '/s'.");
        }

        String searchSkillsStr = parts[1].trim();
        String[] searchSkills = searchSkillsStr.split(",");
        for (int i = 0; i < searchSkills.length; i++) {
            searchSkills[i] = searchSkills[i].trim();
        }

        Ui.printSearchingForSkill(String.join(", ", searchSkills));
        boolean found = false;

        for (Internship internship : internships.getAllInternships()) {
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
                Ui.printLinebreak();
                found = true;
            }
        }

        if (!found) {
            Ui.printNoInternshipFound(String.join(", ", searchSkills));
        }
    }
}
