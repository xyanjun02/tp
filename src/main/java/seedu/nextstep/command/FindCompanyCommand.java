/*
 * FindCompanyCommand.java
 *
 * This class defines the FindCompanyCommand used to search for internships
 * that match a given company provided by the user. The company is provided after
 * the command prefix "find/c". Matching internships are displayed with a
 * line break between each result.
 */

package seedu.nextstep.command;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;
import seedu.nextstep.exception.CommandException;
import seedu.nextstep.ui.Ui;

/**
 * The FindCompanyCommand class processes user input to search for internships
 * that have a company matching the specified query. The company can be provided
 * after the command prefix "find/c".
 */
public class FindCompanyCommand extends Command {

    /**
     * Constructs a new FindCompanyCommand with the specified input.
     *
     * @param input the user input containing the command and company query.
     */
    public FindCompanyCommand(String input) {
        super(input);
    }

    /**
     * Executes the find company command by extracting the company parameter,
     * searching the internships list for any internship whose company matches the
     * specified query, and printing the matching internship details.
     * A line break is printed after each result.
     */
    @Override
    public void execute() {
        try {
            // Split the input into command and company parameter.
            String[] parts = input.split("find/c", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new CommandException("Error: Please specify a company after '/c'.");
            }

            String companyQuery = parts[1].trim();
            // Print the search prompt (assumes corresponding method exists in Ui).
            Ui.printSearchingForCompany(companyQuery);

            boolean found = false;
            // Search through internships for a matching company.
            for (Internship internship : NextStep.internships) {
                if (internship.getCompany().equalsIgnoreCase(companyQuery)) {
                    Ui.printInternship(internship);
                    Ui.printLinebreak();
                    found = true;
                }
            }

            if (!found) {
                // Print message if no matching internship is found.
                Ui.printNoInternshipFoundForCompany(companyQuery);
            }
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input format. Please check the provided command.");
        }
    }
}
