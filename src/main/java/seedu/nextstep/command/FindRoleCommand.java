/*
 * FindRoleCommand.java
 *
 * This class defines the FindRoleCommand used to search for internships
 * based on one or more roles provided by the user. The roles are specified
 * after the command prefix "find/r" as a comma-separated list. Matching internships
 * are displayed with a line break between each result.
 */

package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;
import seedu.nextstep.exception.CommandException;
import seedu.nextstep.ui.Ui;

/**
 * The FindRoleCommand class processes user input to search for internships
 * that have a role matching any of the specified query roles.
 */
public class FindRoleCommand extends Command {

    /**
     * Constructs a new FindRoleCommand with the specified input.
     *
     * @param input the user input containing the command and role query.
     */
    public FindRoleCommand(String input) {
        super(input);
    }

    /**
     * Executes the find role command by extracting the role parameter,
     * splitting it into multiple roles (comma-separated), and searching the
     * internships list for any internship with a role that matches one of the
     * specified queries. A line break is printed between each matching result.
     */
    @Override
    public void execute() {
        try {
            // Split the input into command and role parameter.
            String[] parts = input.split("find/r", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CommandException("Error: Please specify at least one role after '/r'.");
            }

            String roleQueryStr = parts[1].trim();
            // Allow multiple roles, separated by commas.
            String[] searchRoles = roleQueryStr.split(",");
            for (int i = 0; i < searchRoles.length; i++) {
                searchRoles[i] = searchRoles[i].trim();
            }

            // Print the search prompt.
            Ui.printSearchingForRole(String.join(", ", searchRoles));

            boolean found = false;
            // Search through internships for a matching role.
            for (Internship internship : NextStep.internships) {
                for (String searchRole : searchRoles) {
                    if (internship.getRole().equalsIgnoreCase(searchRole)) {
                        Ui.printInternship(internship);
                        Ui.printLinebreak();
                        found = true;
                        // If internship matches one role, no need to check further roles.
                        break;
                    }
                }
            }

            if (!found) {
                Ui.printNoInternshipFoundForRole(String.join(", ", searchRoles));
            }
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }
}
