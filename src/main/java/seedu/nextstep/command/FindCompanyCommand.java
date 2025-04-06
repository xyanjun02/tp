package seedu.nextstep.command;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.ui.Ui;

/**
 * The FindCompanyCommand class processes user input to search for internships
 * that have a company matching the specified query.
 */
public class FindCompanyCommand extends Command {

    /**
     * Constructs a new FindCompanyCommand with the specified input.
     *
     * @param input       the user input containing the command and company query.
     * @param internships the internship list.
     */
    public FindCompanyCommand(String input, InternshipList internships) {
        super(input, internships);
    }

    /**
     * Executes the find company command by extracting the company parameter,
     * searching the internships list for any internship whose company matches the specified query,
     * and printing the matching internship details. A line break is printed after each result.
     *
     * @throws EmptyInputException if no company is provided.
     */
    @Override
    public void execute() throws EmptyInputException {
        String[] parts = input.split("find/c", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new EmptyInputException("Error: Please specify a company after '/c'.");
        }

        String companyQuery = parts[1].trim();
        Ui.printSearchingForCompany(companyQuery);
        boolean found = false;

        String normalizedSearchCompany = companyQuery.replaceAll("\\s+", "").toLowerCase();



        for (Internship internship : internships.getAllInternships()) {
            String normalizedCompany = internship.getCompany().replaceAll("\\s+", " ").trim();
            String companyWithoutSpaces = normalizedCompany.replaceAll("\\s+", "");
            if (companyWithoutSpaces.toLowerCase().contains(normalizedSearchCompany)) {
                Ui.printInternship(internship);
                Ui.printLinebreak();
                found = true;
            }
        }

        if (!found) {
            Ui.printNoInternshipFoundForCompany(companyQuery);
        }
    }
}
