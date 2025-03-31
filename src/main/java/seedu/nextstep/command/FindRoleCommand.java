package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.ui.Ui;

/**
 * The FindRoleCommand class processes user input to search for internships that have a role matching
 * any of the specified queries.
 */
public class FindRoleCommand extends Command {

    /**
     * Constructs a new FindRoleCommand with the specified input.
     *
     * @param input       the user input containing the command and role query.
     * @param internships the internship list.
     */
    public FindRoleCommand(String input, InternshipList internships) {
        super(input, internships);
    }

    /**
     * Executes the find role command by extracting the role parameter, splitting it into multiple roles,
     * and searching the internships list for any internship with a role that matches one of the specified queries.
     * A line break is printed between each matching result.
     *
     * @throws EmptyInputException if no role is provided.
     */
    @Override
    public void execute() throws EmptyInputException {
        String[] parts = input.split("find/r", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyInputException("Error: Please specify at least one role after '/r'.");
        }

        String roleQueryStr = parts[1].trim();
        String[] searchRoles = roleQueryStr.split(",");
        for (int i = 0; i < searchRoles.length; i++) {
            searchRoles[i] = searchRoles[i].trim();
        }

        Ui.printSearchingForRole(String.join(", ", searchRoles));
        boolean found = false;

        for (Internship internship : internships.getAllInternships()) {
            for (String searchRole : searchRoles) {
                if (internship.getRole().equalsIgnoreCase(searchRole)) {
                    Ui.printInternship(internship);
                    Ui.printLinebreak();
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            Ui.printNoInternshipFoundForRole(String.join(", ", searchRoles));
        }
    }
}
