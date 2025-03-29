package seedu.nextstep.command;

import java.util.Scanner;

import seedu.nextstep.NextStep;
import seedu.nextstep.core.Internship;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;

import static seedu.nextstep.ui.Ui.printEditMessage;
import static seedu.nextstep.ui.Ui.printEditSuccess;

/**
 * Represents a command to edit an existing internship entry.
 */
public class EditCommand extends Command {

    /**
     * Constructs an EditCommand object.
     *
     * @param input The user input, which includes the index of the internship to edit.
     */
    public EditCommand(String input) {
        super(input);
    }

    /**
     * Executes the edit command by prompting the user for the fields they wish to edit,
     * collecting updated values, and applying the changes to the internship.
     *
     * @throws EmptyInputException        If the user provides an empty input.
     * @throws InvalidIndexException      If the provided index is out of bounds.
     * @throws InvalidInputFormatException If the user input format is incorrect.
     * @throws NumberFormatException      If numeric values are not properly formatted.
     */
    @Override
    public void execute() throws EmptyInputException, InvalidIndexException, InvalidInputFormatException,
            NumberFormatException {
        Internship internship = getInternship();
        Scanner scanner = new Scanner(System.in);

        // Prompt user for fields to edit
        printEditMessage();
        String fieldsInput = scanner.nextLine().trim();
        if (fieldsInput.isEmpty()) {
            throw new EmptyInputException("Fields to edit cannot be empty.");
        }

        // Split user input into field names
        String[] fieldsToEdit = fieldsInput.split(",\\s*");

        for (String field : fieldsToEdit) {
            switch (field.toLowerCase()) {
                case "company":
                    System.out.print("Updated Company: ");
                    internship.setCompany(scanner.nextLine());
                    break;
                case "role":
                    System.out.print("Updated Role: ");
                    internship.setRole(scanner.nextLine());
                    break;
                case "duration":
                    System.out.print("Updated Duration (in months): ");
                    try {
                        internship.setDuration(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        throw new InvalidInputFormatException("Duration must be a valid number.");
                    }
                    break;
                case "allowance":
                    System.out.print("Updated Allowance ($): ");
                    try {
                        internship.setAllowance(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException e) {
                        throw new InvalidInputFormatException("Allowance must be a valid number.");
                    }
                    break;
                case "skills":
                    System.out.print("Updated Skills (comma-separated): ");
                    internship.setSkills(scanner.nextLine().split(",\\s*"));
                    break;
                default:
                    throw new InvalidInputFormatException(field + " is an invalid field to edit.");
            }
        }
        printEditSuccess(internship);
    }

    private Internship getInternship() throws EmptyInputException, InvalidInputFormatException, InvalidIndexException {
        if (input == null || input.trim().isEmpty()) {
            throw new EmptyInputException("Input cannot be empty");
        }

        // Split input to extract index
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new InvalidInputFormatException("Invalid format. Usage: edit <index>");
        }

        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;  // Convert index to zero-based
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException("Index must be a number.");
        }

        if (index < 0 || index >= NextStep.internships.size()) {
            throw new InvalidIndexException("Invalid index number.");
        }

        return NextStep.internships.get(index);
    }
}
