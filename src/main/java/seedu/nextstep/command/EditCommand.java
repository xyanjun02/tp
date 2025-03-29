/*
 * EditCommand.java
 *
 * This class defines the EditCommand used to edit internship entries
 * that was previously stored by user. The user will then be prompted
 * the fields to be edited and will require to type the updated fields after.
 *
 */

package seedu.nextstep.command;

import java.util.Scanner;

import seedu.nextstep.core.Internship;
import seedu.nextstep.core.InternshipList;
import seedu.nextstep.exception.EmptyInputException;
import seedu.nextstep.exception.InvalidIndexException;
import seedu.nextstep.exception.InvalidInputFormatException;
import seedu.nextstep.storage.Storage;
import seedu.nextstep.ui.Ui;

/**
 * Represents a command to edit an existing internship entry.
 */
public class EditCommand extends Command {
    private final Scanner scanner;
    private final Storage storage;

    /**
     * Constructs an EditCommand object.
     *
     * @param input The user input, which includes the index of the internship to edit.
     */
    public EditCommand(String input, InternshipList internships, Storage storage) {
        super(input, internships);
        this.scanner = new Scanner(System.in);
        this.storage = storage;
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
        Internship internship = getInternshipToEdit();

        // Prompt user for fields to edit
        Ui.printEditMessage();
        String fieldsInput = scanner.nextLine().trim();
        if (fieldsInput.isEmpty()) {
            throw new EmptyInputException("Fields to edit cannot be empty.");
        }

        processFieldEdits(internship, fieldsInput.split(",\\s*"));
        storage.save(internships);
        Ui.printEditSuccess(internship);
    }

    private Internship getInternshipToEdit() throws InvalidIndexException, InvalidInputFormatException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new InvalidInputFormatException("Invalid format. Usage: edit <index>");
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
            return internships.getInternship(index);
        } catch (NumberFormatException e) {
            throw new InvalidInputFormatException("Index must be a number.");
        }
    }

    private void processFieldEdits(Internship internship, String[] fieldsToEdit) throws InvalidInputFormatException {
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
                System.out.print("Updated Duration (months): ");
                try {
                    internship.setDuration(Integer.parseInt(scanner.nextLine()));
                } catch (NumberFormatException e) {
                    throw new InvalidInputFormatException("Duration must be a number");
                }
                break;
            case "allowance":
                System.out.print("Updated Allowance ($): ");
                try {
                    internship.setAllowance(Integer.parseInt(scanner.nextLine()));
                } catch (NumberFormatException e) {
                    throw new InvalidInputFormatException("Allowance must be a number");
                }
                break;
            case "skills":
                System.out.print("Updated Skills (comma-separated): ");
                internship.setSkills(scanner.nextLine().split(",\\s*"));
                break;
            default:
                throw new InvalidInputFormatException(field + " is an invalid field");
            }
        }
    }
}
